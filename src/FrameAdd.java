import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FrameAdd extends JFrame{
    private Book BookNew;
    private  boolean G=true;
    public FrameAdd(BookModel m, Book f1,int rowIndex) {

            JFrame jf = new JFrame("Add new Information");
            jf.setSize(500, 350);
            jf.setVisible(true);
            JPanel panelAdd = new JPanel(new GridLayout(9, 7, 5, 3));
            jf.add(panelAdd, BorderLayout.CENTER);


            JLabel qty1 = new JLabel("qty:");
            panelAdd.add(qty1);
            JTextField qty = new JTextField(Integer.toString(f1.getQty()), 15);
            panelAdd.add(qty);

            JLabel name1 = new JLabel("name:");
            panelAdd.add(name1);
            JTextField name = new JTextField(f1.getName(), 15);
            panelAdd.add(name);

            JLabel year1 = new JLabel("Year:");
            panelAdd.add(year1);
            JTextField year = new JTextField(Integer.toString(f1.getYear()), 15);
            panelAdd.add(year);


            JLabel price1 = new JLabel("price :");
            panelAdd.add(price1);
            JTextField price = new JTextField(Double.toString(f1.getPrice()), 15);
            panelAdd.add(price);

            JLabel AName = new JLabel("Author Name:");
            panelAdd.add(AName);
            JTextField AuthorName = new JTextField(f1.getAuthor().getName(), 15);
            panelAdd.add(AuthorName);


            JLabel Gender1 = new JLabel("Gender:");
            panelAdd.add(Gender1);
            Gender[] genders = {Gender.Female, Gender.Male, Gender.Trans, Gender.Other, Gender.Agender, Gender.Androgyne, Gender.Androgynes, Gender.Androgynous, Gender.Bigender, Gender.Cis, Gender.Cisgender, Gender.FTM, Gender.Genderqueer};
            JComboBox<Gender> gender = new JComboBox<>(genders);
            gender.setSelectedItem(f1.getAuthor().getGender());
            panelAdd.add(gender);

            JLabel Email1 = new JLabel("Email:");
            panelAdd.add(Email1);
            JTextField Email = new JTextField(f1.getAuthor().getEmail(), 15);
            panelAdd.add(Email);

        if (rowIndex == -1) {
            JButton btnClear = new JButton("Clear");
            JButton btnExit = new JButton("Exit");
            JButton btnAdd2 = new JButton("Add");


            JLabel outText = new JLabel("");
            panelAdd.add(outText);


            JPanel panelButton = new JPanel(new GridLayout(1, 3, 12, 0));
            panelButton.add(btnAdd2);
            panelButton.add(btnClear);
            panelButton.add(btnExit);
            jf.add(panelButton, BorderLayout.SOUTH);


            btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    qty.setText("0");
                    name.setText("");
                    Email.setText("");
                    AuthorName.setText("");
                    price.setText("0.0");
                    year.setText("0");


                }
            });

            btnAdd2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String qty1 = qty.getText();
                    String name1 = name.getText();
                    String email = Email.getText();
                    String author = AuthorName.getText();
                    Gender GAdd = (Gender) gender.getSelectedItem();

                    if ((qty1.equals("")) || (name1.equals("")) || (email.equals("")) || (author.equals(""))) {
                        JOptionPane.showMessageDialog(btnAdd2, "All fields must be entered");
                    } else {
                        if (Valid(qty1) == false) {
                            JOptionPane.showMessageDialog(btnAdd2, "Enter correct qty");
                        } else {
                            if (Valid1(price.getText()) == false) {
                                JOptionPane.showMessageDialog(btnAdd2, "Enter correct price");

                            }
                            else {
                                if (email.contains("@")==false) {
                                    JOptionPane.showMessageDialog(btnAdd2, "Email must contain  '@' ");

                                }
                                else {
                                Author AuthorNew = new Author(AuthorName.getText(), Email.getText(), GAdd);
                                BookNew = new Book(name1, Integer.parseInt(qty1), Integer.parseInt(year.getText()), Double.parseDouble(price.getText()), AuthorNew);
                                Swing.setI(true);
                                G = false;
                                outText.setText("Success!");

                                m.addBook(BookNew);
                            }
                        }
                    }}
                }
            });
            ActionListener exitAction = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (G) {
                        int res = JOptionPane.showConfirmDialog(btnExit, "Are you sure want to exit?" , "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.YES_OPTION) {
                            jf.dispose();

                        }
                    } else jf.dispose();
                }
            };
            btnExit.addActionListener(exitAction);
        }
        else{
            JButton btnEdit = new JButton("Edit");
            JButton btnReturn = new JButton("Return");
            JButton btnExit = new JButton("Exit");
            JPanel panelButton = new JPanel(new GridLayout(1, 3, 12, 0));

            panelButton.add(btnEdit);
            panelButton.add(btnReturn);
            panelButton.add(btnExit);

            jf.add(panelButton, BorderLayout.SOUTH);

            btnReturn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    qty.setText(Integer.toString(f1.getQty()));
                    name.setText(f1.getName());
                    Email.setText(f1.getAuthor().getEmail());
                    AuthorName.setText(f1.getAuthor().getName());
                    price.setText(Double.toString(f1.getPrice()));
                    year.setText(Integer.toString(f1.getYear()));

                }
            });

            btnEdit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String qty1 = qty.getText();
                    String name1 = name.getText();
                    String email = Email.getText();
                    String author = AuthorName.getText();
                    Gender GAdd = (Gender) gender.getSelectedItem();

                    if ((qty1.equals("")) || (name1.equals("")) || (email.equals("")) || (author.equals(""))) {
                        JOptionPane.showMessageDialog(btnEdit, "All fields must be entered");
                    } else {
                        if (Valid(qty1) == false) {
                            JOptionPane.showMessageDialog(btnEdit, "Enter correct qty");
                        } else {
                            if (Valid1(price.getText()) == false) {
                                JOptionPane.showMessageDialog(btnEdit, "Enter correct price");
                            }
                                 else {
                                    if (email.contains("@")==false) {
                                        JOptionPane.showMessageDialog(btnEdit, "Email must contain  '@' ");

                                    }
                            else {
                                Author AuthorNew = new Author(AuthorName.getText(), Email.getText(), GAdd);
                                BookNew = new Book(name1, Integer.parseInt(qty1), Integer.parseInt(year.getText()), Double.parseDouble(price.getText()), AuthorNew);
                                Swing.setI(true);
                                G = false;

                                m.addBook(f1,BookNew,rowIndex);
                                jf.dispose();
                            }
                        }}
                    }
                }

            });
            ActionListener exitAction = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                     {
                        int res = JOptionPane.showConfirmDialog(btnExit, "Are you sure want to exit?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.YES_OPTION) {
                            jf.dispose();

                        }
                    }
                }
            };
            btnExit.addActionListener(exitAction);

        }
    }



        private boolean Valid (String str){
            try {
                int QTY = Integer.parseInt(str);
                if (QTY > 0)
                    return true;
                else return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        private boolean Valid1 (String str){

            try {
                double Price = Double.parseDouble(str);
                if (Price > 0) {
                    return true;
                } else return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }


}
