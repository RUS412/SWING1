
import java.util.Objects;
enum Gender{Agender,Androgyne, Androgynes,Androgynous,Bigender, Cis,Female,Male,Cisgender,FTM,Genderqueer,Trans,Other}
public class Author {
    private String name;
    private String email;
    private Gender gender;

    public Author(String name, String email, Gender gender) {
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return  name + " " +
                " " + email + " " +
                 gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
