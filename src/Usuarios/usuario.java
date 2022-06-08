package Usuarios;

import java.time.LocalDateTime;
import java.util.UUID;

public class usuario {
        private String email;
        private UUID code64 = UUID.randomUUID();
        private String password;
        private double utnCoins;
        private String apellido;
        private String dni;
        private LocalDateTime local;
        // private datePicker;
        private int edad;


        public usuario(String email, String password, double utnCoins, String apellido, String dni, LocalDateTime local, int edad) {
                this.email = email;
                this.password = password;
                this.utnCoins = utnCoins;
                this.apellido = apellido;
                this.dni = dni;
                this.local = local;
                this.edad = edad;
        }

        public int getEdad() {return edad;}

        public void setEdad(int edad) {this.edad = edad;}

        public String getEmail() {return email;}

        public void setEmail(String email) {this.email = email;}

        public UUID getCode64() {return code64;}

        public void setCode64(UUID code64) {this.code64 = code64;}

        public String getPassword() {return password;}

        public void setPassword(String password) {this.password = password;}

        public double getUtnCoins() {return utnCoins;}

        public void setUtnCoins(double utnCoins) {this.utnCoins = utnCoins;}

        public String getApellido() {return apellido;}

        public void setApellido(String apellido) {this.apellido = apellido;}

        public String getDni() {return dni;}

        public void setDni(String dni) {this.dni = dni;}

        public LocalDateTime getLocal() {return local;}

        public void setLocal(LocalDateTime local) {this.local = local;}
}
