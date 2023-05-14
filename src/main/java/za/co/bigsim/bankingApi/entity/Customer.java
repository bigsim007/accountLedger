package za.co.bigsim.bankingApi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String EmailId;
    private long ContactNo;

   // @OneToMany(mappedBy="users")
    //private List<Account> account ;

   // @OneToMany(mappedBy="targetCustomer")
   // private List<Transaction> transaction ;

    //public List<Transaction> getTransaction() {
   //     return transaction;
  //  }

   // public List<Account> getAccount() {
   //     return account;
   // }

    //public void setAccount(List<Account> account) {
    //    this.account = account;
   // }

    public void setTransaction(List<Transaction> transaction) {
        transaction = transaction;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmailId() {
        return EmailId;
    }
    public void setEmailId(String emailId) {
        EmailId = emailId;
    }
    public long getContactNo() {
        return ContactNo;
    }
    public void setContactNo(long contactNo) {
        ContactNo = contactNo;
    }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
