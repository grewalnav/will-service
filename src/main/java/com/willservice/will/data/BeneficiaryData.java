package com.willservice.will.data;

public class BeneficiaryData {

        private String firstName;
        private String lastName;
        private String middleName;
        private AddressData address;

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getMiddleName() {
                return middleName;
        }

        public void setMiddleName(String middleName) {
                this.middleName = middleName;
        }

        public AddressData getAddress() {
                return address;
        }

        public void setAddress(AddressData address) {
                this.address = address;
        }

        public String getBeneficiaryFullName(){
                String name = "";
                if(this.getFirstName() != null){
                        name = this.getFirstName();
                }
                if(this.getLastName() != null){
                        name += name.length() > 0 ? " " : "";
                        name += this.getLastName();
                }
                return name;
        }
}
