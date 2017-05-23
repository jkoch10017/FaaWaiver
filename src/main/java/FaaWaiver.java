import DataConnection.DataConnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jkoch on 5/19/17.
 * <p>
 * ***********************************************************************
 * <p>
 * Pilot Training System CONFIDENTIAL
 * __________________
 * <p>
 * [2015] - [2017] Pilot Training System
 * All Rights Reserved.
 * <p>
 * NOTICE:  All information contained herein is, and remains
 * the property of Pilot Training System,
 * The intellectual and technical concepts contained
 * herein are proprietary to Pilot Training System
 * and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Pilot Training System.
 */


public class FaaWaiver extends Application {


    private static DataConnection mDataConnection = DataConnection.dataConnection("facility_map1");

    public static void main(String[] args) {
        System.out.println("FaaWaiver");

        mDataConnection.syncFromCloud();

        Map<String, String> map = new HashMap<>();

        String closest = mDataConnection.closestDocument("geoidx", 40, -90);
        System.out.println("closest = " + closest);

        mDataConnection.syncToCloud();

        // Launch the app.
        launch(args);
    }


    // =============  GUI ====================

    // The main Java FX structure used to manage the various widgets.
    // We are trying to emulate the layout of the FAA request_waiver page.
    GridPane mGridPane = new GridPane();
    Button mGenerateButton = new Button();

    final ComboBox mPilotComboBox = new ComboBox();
    public void start(Stage primaryStage) {

        //============== Main Grid Pane ===============
        // mGridPane holds all the other controls for the application.
        // This performs basic formatting.
        formatGridPane(mGridPane);
        int index=0;

        //  Tomorrow, get the list of Pilots from the database. 
        mGridPane.addRow(index++,new Label("Pilot"), mPilotComboBox);
       // mPilotComboBox.getItems().addAll("bill", "mary", "cindy");
        List    tempList = new ArrayList() ;
        tempList.add("Sally");
        tempList.add("Jolly");
        tempList.add("Holly");

        mPilotComboBox.getItems().addAll(tempList);

        mGenerateButton.setText("Generate Waiver Request");

        mGridPane.addRow(index++, mGenerateButton);


        Scene scene = new Scene(mGridPane);
        primaryStage.setTitle("FAA Waiver");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * Performs the formatting for the main screen of the application.
     */
    private void formatGridPane(GridPane gridPane) {

        gridPane.setAlignment(Pos.TOP_CENTER);
        // Set the gap between all the items in the grid.
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Set the gap around the edges.
        gridPane.setPadding(new Insets(25));

        // Draw an thin black line around the borders.
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

    }

}