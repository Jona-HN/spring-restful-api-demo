package com.uabc.jonahn.springweb.LoanRequest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class LoanRequest {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String applicantName;

    @NonNull
    private Float loanAmount;

    private boolean approved;

    private String term;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest)) return false;

        LoanRequest loanReq = (LoanRequest) o;

        return Objects.equals(this.id, loanReq.id) &&
               Objects.equals(this.applicantName, loanReq.applicantName) &&
               Objects.equals(this.approved, loanReq.approved) &&
               Objects.equals(this.term, loanReq.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicantName, approved, term);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "id=" + id +
                ", applicantName='" + applicantName + '\'' +
                ", loanAmount=" + loanAmount +
                ", approved=" + approved +
                ", term='" + term + '\'' + '}';
    }

}
