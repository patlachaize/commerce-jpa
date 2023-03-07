package ch.etml.pl.commerce;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "items", schema = "commerce", catalog = "")
public class ItemsEntity {
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
    @Basic
    @Column(name = "client")
    private Integer client;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "num")
    private ClientsEntity clientsByClient;

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

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsEntity that = (ItemsEntity) o;
        return num == that.num && Objects.equals(description, that.description) && Objects.equals(prix, that.prix) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, description, prix, client);
    }

    public ClientsEntity getClientsByClient() {
        return clientsByClient;
    }

    public void setClientsByClient(ClientsEntity clientsByClient) {
        this.clientsByClient = clientsByClient;
    }
}
