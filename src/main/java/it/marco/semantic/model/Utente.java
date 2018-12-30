package it.marco.semantic.model;

@SuppressWarnings("deprecation")
public class Utente {


    private static Utente instance;
    private String nome;
    private String id;
    private String username;
    private String password;
    private String AlfrescoTicket;
    private String role;

    public static void setInstance(Utente instance) {
        Utente.instance = instance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utente() {

    }

    public static Utente getInstance() {
        if (instance == null) {
            instance = new Utente();
        }
        return instance;
    }

    public String getAlfrescoTicket() {
        return AlfrescoTicket;
    }

    public void setAlfrescoTicket(String alfrescoTicket) {
        AlfrescoTicket = alfrescoTicket;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
