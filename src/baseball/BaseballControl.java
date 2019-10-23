
package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.bind.JAXB;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author T-Money
 */
public class BaseballControl implements Initializable{
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



    /**
     * 
     * Variables that are a mapping to the FXML controls
     * 
     */
    @FXML private ListView listview;
    @FXML private TableView tableview;
    @FXML private TableColumn <String, Teams> tablecolumn1;
    @FXML private TableColumn <String, Teams> tablecolumn2;
    
    
    //ArrayList of States objects
    private final List<Teams> teams = new ArrayList<>();
    
    //Gets the directory path of the project
    private final String DIR = System.getProperty("user.dir");
          
    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        readFile();
        
        //ChangeListerner for when you click on a ListView Item
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               
                //Find the state chosen in the ArrayList
                int idx = findTeam(newValue);
                
                //Link the column's cell value to the get function of the States Class.
                //REMEMBER the name in the quotation marks should match the case of the get function
                tablecolumn1.setCellValueFactory(new PropertyValueFactory<>("Name"));
                tablecolumn2.setCellValueFactory(new PropertyValueFactory<>("BattingAvg"));

                
                //Create an ObservableList object to store the States object(s)
                ObservableList<Teams> result = FXCollections.observableArrayList();
                
                //Add the States object to the list
                result.add(teams.get(idx));
              
                //Bind the list  to the table
                tableview.setItems(result);
                
            }
        });
    }
    
    /**
     * Sequential search of the states by name
     * @param value
     * @return 
     */
    
    private int findTeam(String value){
     
        for(int i = 0; i < teams.size(); i++)
            if(teams.get(i).getTeam().equals(value))
                return i;
        return 0;
    }
    
    /**
     * Read file contents and populate the Lists and ListView
     */
    private void readFile() {
        
        //ObservableList to add states to the ListView
        ObservableList<String> teamList = FXCollections.observableArrayList();
        
        //Open csv file for input
        try(BufferedReader csvReader = Files.newBufferedReader(Paths.get(DIR + "/src/baseball/data/baseball.xml"))){
        MLBReader baseballteam;
            baseballteam = JAXB.unmarshal(csvReader, MLBReader.class);

                //add object to ArrayList
            for (Teams team : baseballteam.getTeamName()){
                teams.add(team);
                //System.out.println(team.toString());
                //add state name to ObservableList
              if (!teamList.contains(team.getTeam())){
                teamList.add(team.getTeam());
               }
            }
            
        
        }catch(IOException e){
            System.err.println("Error openning file");
            e.printStackTrace();
        }
        
        //Sorts the List of State objects by state name in ascending order
        Collections.sort(teams, new BaseballComparator());
        //sorts the ObservableList
        Collections.sort(teamList);
        
        //Binds the ObservableList to the ListView
        listview.setItems(teamList);
        System.out.println(teamList.size());
    }
}



    

