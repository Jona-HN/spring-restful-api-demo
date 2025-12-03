package com.uabc.jonahn.springweb.LoanRequest.enums;

public enum Status {

    IN_PROGRESS {
        @Override
        public String toString() {
            return "In Progress";
        }
    },
    APPROVED {
        @Override
        public String toString() {
            return "Approved";
        }
    },
    REJECTED {
        @Override
        public String toString() {
            return "Rejected";
        }
    },

}
