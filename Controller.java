package FX.Graphics.KochCurve;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private AnchorPane pane;

    private String bgColor = "linear-gradient(to bottom, #147aac, #0c648e, #064f71, #023b56, #00283c)";
    private List<Line> lineList = new LinkedList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setStyle("-fx-background-color: " + bgColor);
        lineList.add(line(0,2*pane.getPrefHeight()/3,pane.getPrefWidth(),2*pane.getPrefHeight()/3));
        pane.getChildren().addAll(lineList);
        pane.setOnMouseClicked(this::handle);
    }

    private void handle(MouseEvent mouseEvent) {
        List<Line> newList = new LinkedList<>();
        for (Line l: lineList) {
            double startX = l.getStartX();
            double startY = l.getStartY();
            double endX = l.getEndX();
            double endY = l.getEndY();

            double length = Math.sqrt(Math.pow(endX-startX,2)+Math.pow(endY-startY,2));


            double theta = -Math.PI/3;
            double x = (endX - startX)/3;
            double y = (endY - startY)/3;
            double xP = x*Math.cos(theta) - y*Math.sin(theta);
            double yP = y*Math.cos(theta) + x*Math.sin(theta);


            double ax = startX;
            double ay = startY;
            double bx = ax + (endX-startX)/3;
            double by = ay + (endY-startY)/3;
            double cx = bx + xP;
            double cy = by + yP;
            double dx = endX - ((endX - startX)/3);
            double dy = endY - ((endY-startY)/3);
            double ex = endX;
            double ey = endY;

            Line one = line(ax,ay,bx,by);
            newList.add(one);
            Line two = line(bx,by,cx,cy);
            newList.add(two);
            Line three = line(cx,cy,dx,dy);
            newList.add(three);
            Line four = line(dx,dy,ex,ey);
            newList.add(four);
        }
        lineList = newList;
        pane.getChildren().clear();
        pane.getChildren().addAll(lineList);
    }
    private Line line (double x1, double y1, double x2, double y2) {
        Line l = new Line(x1,y1,x2,y2);
        l.setStroke(Color.WHITE);
        return l;
    }
}
