package Usuarios;


import java.time.LocalDateTime;
import java.util.UUID;

public class Usuario{
        private String email;
        private UUID code64;
        private String password;
        private double utnCoins;
        private String apellido;
        private String nombre;
        private String dni;
        

		private LocalDateTime issuedDate;



        public Usuario() {}

        public Usuario(String email, String password, String nombre,String apellido, String dni, LocalDateTime issued) {
        		this.code64=UUID.randomUUID();
                this.email = email;
                this.password = password;
                this.utnCoins = 100;
                this.nombre=nombre;
                this.apellido = apellido;
                this.dni = dni;
                this.issuedDate = issued;
        }

        public String getNombre() {
			return nombre;
		}

		public LocalDateTime getIssuedDate() {
			return issuedDate;
		}

		public String getEmail() {return email;}

        public void setEmail(String email) {this.email = email;}

        public String getCode64() {return code64.toString();}

        public void setCode64(UUID code64) {this.code64 = code64;}

        public String getPassword() {return password;}

        public void setPassword(String password) {this.password = password;}

        public double getUtnCoins() {return utnCoins;}

        public String getApellido() {return apellido;}

        public void setApellido(String apellido) {this.apellido = apellido;}

        public String getDni() {return dni;}

        public void setDni(String dni) {this.dni = dni;}

        public LocalDateTime getLocal() {return issuedDate;}

        public void setLocal(LocalDateTime issued) {this.issuedDate = issued;}
        
        public void setNombre(String nombre) {this.nombre = nombre;}
        
        public void agregaSaldo (double saldo) {this.utnCoins+=saldo;}
        
        public void setUtnCoins(double utnCoins) {this.utnCoins = utnCoins;}

		@Override
        public String toString() {
        	return "Usuario: "+getCode64()+" Apellido: "+getApellido()+" Email: "+getEmail()+" Saldo actual: "+getUtnCoins();
        	
        }
}
