package com.uabc.jonahn.springweb.LoanRequest.models;

import com.uabc.jonahn.springweb.LoanRequest.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LoanRequest {

    @Id
    @GeneratedValue
    private Long id;

    private String applicantFirstName;

    private String applicantLastName;

    @NonNull
    private Float loanAmount;

    @NonNull
    private Status status;

    private String term;

    public LoanRequest(String applicantFirstName, String applicantLastName, @NonNull Float loanAmount, @NonNull Status status) {
        this.applicantFirstName = applicantFirstName;
        this.applicantLastName = applicantLastName;
        this.loanAmount = loanAmount;
        this.status = status;
    }

    // "Virtual" getter for old property 'applicantName'
    public String getApplicantName() {
        return applicantFirstName + " " + applicantLastName;
    }

    // "Virtual" setter for old property 'applicantName'
    public void setApplicantName(String applicantName) {
        String[] nameSplitted = applicantName.split(" ");
        applicantFirstName = nameSplitted[0];
        applicantLastName = nameSplitted[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest)) return false;

        LoanRequest loanReq = (LoanRequest) o;

        return Objects.equals(this.id, loanReq.id) &&
               Objects.equals(this.applicantFirstName, loanReq.applicantFirstName) &&
               Objects.equals(this.applicantLastName, loanReq.applicantLastName) &&
               Objects.equals(this.status, loanReq.status) &&
               Objects.equals(this.term, loanReq.term);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicantFirstName, applicantLastName, status, term);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "id=" + id +
                ", applicantFirstName='" + applicantFirstName + '\'' +
                ", applicantLastName='" + applicantLastName + '\'' +
                ", loanAmount=" + loanAmount +
                ", status=" + status +
                ", term='" + term + '\'' + '}';
    }

}
