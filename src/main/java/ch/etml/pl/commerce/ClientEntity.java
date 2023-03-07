package ch.etml.pl.commerce;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num")
    private int num;
    @Basic
    @Column(name = "prenom", length = 10)
    private String prenom;
    @Basic
    @Column(name = "solde")
    private BigDecimal solde;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return num == that.num && Objects.equals(prenom, that.prenom) && Objects.equals(solde, that.solde);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, prenom, solde);
    }

}
