App.class : Controller.class Adresse.class AdresseIp.class Masque.class Reseau.class
	javac --module-path "../lib" --add-modules=javafx.fxml,javafx.controls App.java

Controller.class : Reseau.class
	javac --module-path "../lib" --add-modules=javafx.fxml,javafx.controls com/sem/controller/Controller.java

Adresse.class :
	javac com/sem/reseau/adresse/Adresse.java

AdresseIp.class : Adresse.class
	javac com/sem/reseau/AdresseIp.java

Masque.class : Adresse.class
	javac com/sem/reseau/Masque.java

Reseau.class : AdresseIp.class Masque.class
	javac com/sem/reseau/Reseau.java
