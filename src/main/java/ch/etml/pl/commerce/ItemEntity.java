package ch.etml.pl.commerce;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "items")
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num")
    private int num;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "prix")
    private BigDecimal prix;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "num")
    private ClientEntity client;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return num == that.num && Objects.equals(description, that.description) && Objects.equals(prix, that.prix) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, description, prix, client);
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
