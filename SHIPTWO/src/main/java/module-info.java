module com.shipzz.gamez.shiptwo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.shipzz.gamez.shiptwo to javafx.fxml;
    exports com.shipzz.gamez.shiptwo;
}