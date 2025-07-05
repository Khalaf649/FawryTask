package model.user;

public abstract class User {
    protected String name;
    protected String email;

    public User(String name, String email) {
        setName(name);
        setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("User name must not be empty.");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
        this.email = email;
    }


    @Override
    public String toString() {
        return getRole() + ": " + name + " <" + email + ">";
    }

    public String getRole() {
        return this.getClass().getSimpleName();
    }

}
