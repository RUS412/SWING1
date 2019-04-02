package com.netcracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Swing extends JFrame {
   static private  boolean changes=false;

   static public void setChanges(boolean i) {
       changes = i;
    }

    public static boolean isI() {
        return changes;
    }
  static  private double a=2.91;

    public Swing() throws IOException {

       // super("CommandButtons");
        setSize(600, 250);
        setLocation(500, 200);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        BookModel m=new BookModel();
        m.BookRun();
        JTable table=new JTable(m);
        JScrollPane jScrollPane=new JScrollPane(table);
        add(jScrollPane);
        setVisible(true);

/*       dispose();
        setUndecorated(true);
        setVisible(true);*/

        JButton buttonAdd = new JButton("Add");
        JButton buttonDelete = new JButton("Delete");
        JButton buttonEdit = new JButton("Edit");
        JButton buttonSave = new JButton("Save");
        JButton buttonExit = new JButton("Exit");
        JButton button = new JButton("PI="+a );
        JPanel grid = new JPanel(new GridLayout(6, 1, 12, 0));

        grid.add(button);
        grid.add(buttonAdd);
        grid.add(buttonDelete);
        grid.add(buttonEdit);
        grid.add(buttonSave);
        grid.add(buttonExit);
        add(grid, BorderLayout.EAST);

        ActionListener a1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               a=a+0.01;
               a=Math.round(a * 100.0) / 100.0;
              button.setText("PI="+a);
              if(a==3.14) {

                  Button a1=new Button();
                  a=2.91;
              }

            }
        };
        button.addActionListener(a1);

        ActionListener addInfo = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Author startAuthor = new Author("","", Gender.Other);
                Book startBookModel  = new Book( "", 0,0, 0, startAuthor);
                FrameAdd frameAdd = new FrameAdd(m,startBookModel,-1);
            }
        };
        buttonAdd.addActionListener(addInfo);




        ActionListener exitAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(changes==true) {
                    int res = JOptionPane.showConfirmDialog(buttonExit, "Want to exit without saving?" , "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
                else  dispose();
            }
        };
        buttonExit.addActionListener(exitAction);
        ActionListener saveInfo = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter wr = new FileWriter("TXT.txt");
                    BufferedWriter bufwr = new BufferedWriter(wr);
                    for (Book f : m.books ){
                        bufwr.write(f.toString());
                        bufwr.newLine();
                    }
                    changes=false;
                    bufwr.close();
                    wr.close();
                }
                catch(IOException ex){ex.printStackTrace();}
            }
        };
        buttonSave.addActionListener(saveInfo);
        ActionListener editInfo = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectRows = table.getSelectedRows();
                if(selectRows.length!=1){
                    JOptionPane.showMessageDialog(buttonEdit,"Select one row");
                }
                else{
                    BookModel model = (BookModel)table.getModel();
                    Book selectBook=model.getBook(selectRows[0]);
                    FrameAdd Edit = new FrameAdd(m,selectBook,selectRows[0]);
                    table.updateUI();
                }
            }
        };
        buttonEdit.addActionListener(editInfo);

        ActionListener deleteInfo = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectRows = table.getSelectedRows();
                BookModel model = (BookModel)table.getModel();
                for (int i=selectRows.length-1;i>=0;i--){
                    int delIndex = selectRows[i];
                    m.deleteBook(model.getBook(delIndex));
                }
                changes=true;
                table.updateUI();
            }
        };
        buttonDelete.addActionListener(deleteInfo);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Swing();
                } catch (IOException e) {
                   // e.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),"File can't be opened");
                }
            }
        });
    }
}


