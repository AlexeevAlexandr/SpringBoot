package hello;

public class PersonPassword {


        private String password;

        public PersonPassword() {

        }

        public PersonPassword(int id, String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
