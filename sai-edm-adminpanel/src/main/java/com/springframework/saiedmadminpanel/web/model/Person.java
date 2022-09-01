package com.springframework.saiedmadminpanel.web.model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.type.BlobType;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", nullable = false, length = 150)
    private String username;
    @Column(name = "first_name", nullable = false, length = 150)
    private String first_name;
    @Column(name = "last_name", nullable = false, length = 150)
    private String last_name;
    @Column(name = "patronymic", nullable = true, length = 150)
    private String patronymic;
    @Column(name = "email", nullable = true, length = 254)
    private String email;
    @Column(name = "is_staff", nullable = true)
    private Boolean is_staff;
    @Column(name = "is_active", nullable = true)
    private Boolean is_active;
    @Column(name = "date_joined", nullable = true)
    private Date date_joined;
    @OneToOne
    @JoinColumn(name = "position",referencedColumnName = "id", nullable = true)
    private Position position;
    @Column(name = "born_date", nullable = true)
    private Date born_date;
    @Column(name = "phone", nullable = true, length = 50)
    private String phone;
    @Column(name = "address", nullable = true)
    private String address;
    @OneToOne
    @JoinColumn(name = "deputy",referencedColumnName = "id", nullable = true)
    private Person deputy;
    @Column(name = "avatar", nullable = true, length=500)
    private String avatar;
    @Column(name = "password")
    private String password;
    @Column(name = "notes", nullable = true, length = 2000)
    private String notes;
    @Column(name = "is_deleted", nullable = true)
    private Boolean is_deleted;

    @ManyToMany
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public Person() {
    }

    public Person(int id, String username, String first_name, String last_name, String patronymic, String email, Boolean is_staff, Boolean is_active, Date date_joined, Position position, Date born_date, String phone, String address, Person deputy, String avatar, String password, String notes, Boolean is_deleted, List<Role> roles) {

        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.email = email;
        this.is_staff = is_staff;
        this.is_active = is_active;
        this.date_joined = date_joined;
        this.position = position;
        this.born_date = born_date;
        this.phone = phone;
        this.address = address;
        this.deputy = deputy;
        this.avatar = avatar;
        this.password = password;
        this.notes = notes;
        this.is_deleted = is_deleted;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(Boolean is_staff) {
        this.is_staff = is_staff;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getBorn_date() {
        return born_date;
    }

    public void setBorn_date(Date born_date) {
        this.born_date = born_date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getDeputy() {
        return deputy;
    }

    public void setDeputy(Person deputy) {
        this.deputy = deputy;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
