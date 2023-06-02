package fr.amu.iut.cc3;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToileController implements Initializable {

    private static int rayonCercleExterieur = 200;
    private static int angleEnDegre = 60;
    private static int angleDepart = 90;
    private static int noteMaximale = 20;

    @FXML
    private Pane toile;

    @FXML
    private Label messageErreur;
    @FXML
    private TextField comp1 = new TextField();

    @FXML
    private TextField comp2;

    @FXML
    private TextField comp3;

    @FXML
    private TextField comp4;

    @FXML
    private TextField comp5;

    @FXML
    private TextField comp6;

    private Circle dot1;
    private Circle dot2;
    private Circle dot3;
    private Circle dot4;
    private Circle dot5;
    private Circle dot6;

    private Line l1;
    private Line l2;
    private Line l3;
    private Line l4;
    private Line l5;
    private Line l6;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    int getXRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur + Math.cos(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale));
    }

    int getYRadarChart(double value, int axe ){
        return (int) (rayonCercleExterieur - Math.sin(Math.toRadians(angleDepart - (axe-1)  * angleEnDegre)) * rayonCercleExterieur
                *  (value / noteMaximale));
    }

    @FXML
    public void tracer(){
        int n1 = Integer.parseInt(comp1.getText());
        int n2 = Integer.parseInt(comp2.getText());
        int n3 = Integer.parseInt(comp3.getText());
        int n4 = Integer.parseInt(comp4.getText());
        int n5 = Integer.parseInt(comp5.getText());
        int n6 = Integer.parseInt(comp6.getText());

        if (n1 > 20 || n1 < 0 || n2 > 20 || n2 < 0 || n3 > 20 || n3 < 0 || n4 > 20 || n4 < 0 || n5 > 20 || n5 < 0 || n6 > 20 || n6 < 0 ) {
            messageErreur.setStyle("-fx-text-fill: red");
            messageErreur.setVisible(true);
        }
        else {

            messageErreur.setVisible(false);

            dot1 = new Circle(10, 20, 5, Color.BLACK);
            dot2 = new Circle(10, 20, 5, Color.BLACK);
            dot3 = new Circle(10, 20, 5, Color.BLACK);
            dot4 = new Circle(10, 20, 5, Color.BLACK);
            dot5 = new Circle(10, 20, 5, Color.BLACK);
            dot6 = new Circle(10, 20, 5, Color.BLACK);

            dot1.setCenterX(getXRadarChart(n1, 1));
            dot1.setCenterY(getYRadarChart(n1, 1));

            dot2.setCenterX(getXRadarChart(n2, 2));
            dot2.setCenterY(getYRadarChart(n2, 2));

            dot3.setCenterX(getXRadarChart(n3, 3));
            dot3.setCenterY(getYRadarChart(n3, 3));

            dot4.setCenterX(getXRadarChart(n4, 4));
            dot4.setCenterY(getYRadarChart(n4, 4));

            dot5.setCenterX(getXRadarChart(n5, 5));
            dot5.setCenterY(getYRadarChart(n5, 5));

            dot6.setCenterX(getXRadarChart(n6, 6));
            dot6.setCenterY(getYRadarChart(n6, 6));

            l1 = new Line(dot1.getCenterX(), dot1.getCenterY(), dot2.getCenterX(), dot2.getCenterY());
            l2 = new Line(dot2.getCenterX(), dot2.getCenterY(), dot3.getCenterX(), dot3.getCenterY());
            l3 = new Line(dot3.getCenterX(), dot3.getCenterY(), dot4.getCenterX(), dot4.getCenterY());
            l4 = new Line(dot4.getCenterX(), dot4.getCenterY(), dot5.getCenterX(), dot5.getCenterY());
            l5 = new Line(dot5.getCenterX(), dot5.getCenterY(), dot6.getCenterX(), dot6.getCenterY());
            l6 = new Line(dot6.getCenterX(), dot6.getCenterY(), dot1.getCenterX(), dot1.getCenterY());

            toile.getChildren().addAll(dot1, dot2, dot3, dot4, dot5, dot6, l1, l2, l3, l4, l5, l6);
        }
    }

    @FXML
    public void clear(){
        comp1.setText("");
        comp2.setText("");
        comp3.setText("");
        comp4.setText("");
        comp5.setText("");
        comp6.setText("");
        toile.getChildren().clear();
    }
}
