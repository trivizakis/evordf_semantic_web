package ontologyevolutionvisualizer.virtuososervice;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
//import com.hp.hpl.jena.query.ResultSetFormatter;
//import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import ontologyevolutionvisualizer.OntoConstants;
//import org.json.JSONArray;
//import org.json.JSONObject;

/**
 *
 * @author Antonis
 */
public class VirtuosoService {
    //private static final ArrayList<String> propertyUri = new ArrayList<>(Arrays.asList(
    //        "E66.Formation"));
        
    private static String changeOperatorChoice;
    private static String propertyChoice;
    
    public VirtuosoService() {
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> justRun(){ //for debuging or maybe not?
        String query;
        String[] queries;
        ArrayList<ArrayList<ArrayList<String>>> results = new ArrayList<>();
        ArrayList<String> fileChOp = new ArrayList<>();
        ArrayList<ResultSet> rs = new ArrayList<>();
            //first query - first file
        System.out.println("---------------------1----------------------");
            fileChOp.add(changeOperatorChoice);
            query = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(0), getChangeOperatorChoice(), getPropertyChoice());
            rs.add(fetchResults(query));
            //results.addAll(0,getNodes(rs));
            if(!rs.isEmpty()){
                ArrayList<ArrayList<ArrayList<String>>> temp = getNodes(rs);
                        for(int k=0; k<temp.size(); k++){
                            if(!temp.get(k).isEmpty())
                                results.add(temp.get(k));
                        }
            }
            rs.clear();
//            for(int j=0; j<results.get(0).size(); j++){
//                        if(getChangeOperatorChoice().equals("RENAME_CLASS")
//                                ||getChangeOperatorChoice().equals("RENAME_PROPERTY"))
//                                    setProperty(results.get(0).get(j).get(2));
//            }
            int oneUp=3;
            //second wave queries - first file => αν στο ίδιο αρχείο υπάρχουν άλλες αλλαγές
            queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(0), Integer.valueOf(changeOperatorChoice), getPropertyChoice());
            for(int i=0; i<queries.length; i++){
                if(oneUp==0)//if(queries[i].length()<1 || oneUp==0) //alliws exception
                           break;//failsafe
                if(!queries[i].equals("")){ //ola ta alla ektos to epilegmeno changeOperator
                    rs.add(fetchResults(queries[i]));
                    if(!rs.isEmpty()){
                        setChangeOperator(""+i);
                        //results.addAll(1,getNodes(rs));
                        ArrayList<ArrayList<ArrayList<String>>> temp1 = getNodes(rs);
                        for(int k=0; k<temp1.size(); k++){
                                fileChOp.add(changeOperatorChoice);
                                results.add(temp1.get(k));
                        }
                    }
                    rs.clear();                    
                    if(getChangeOperatorChoice().equals("RENAME_CLASS")
                       ||getChangeOperatorChoice().equals("RENAME_PROPERTY")){
                            queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(0), Integer.valueOf(changeOperatorChoice), getPropertyChoice());
                            for(int j=0; j<fileChOp.size(); j++){
                                queries[Integer.valueOf(fileChOp.get(j))]="";
                            i=0;}
                            oneUp--;
                    }
                }
            }
            fileChOp.clear();                    
        oneUp = 3;
            //third wave queries - second file => αν στο δευτερο αρχείο υπάρχουν αλλαγές
        System.out.println("---------------------2----------------------");
            queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(1), -1, getPropertyChoice());
            for(int i=0; i<queries.length; i++){
                if(oneUp==0)//if(queries[i].length()<1 || oneUp==0) //alliws exception
                           break;//failsafe
                if(!queries[i].equals("")){ //ola ta alla ektos to epilegmeno changeOperator
                    rs.add(fetchResults(queries[i]));
                    if(!rs.isEmpty()){
                        setChangeOperator(""+i);
                        ArrayList<ArrayList<ArrayList<String>>> temp1 = getNodes(rs);
                        for(int k=0; k<temp1.size(); k++){
                                fileChOp.add(changeOperatorChoice);
                                results.add(temp1.get(k));
                        }
                    }
                    
                    rs.clear(); 
                        if(getChangeOperatorChoice().equals("RENAME_CLASS")
                                ||getChangeOperatorChoice().equals("RENAME_PROPERTY")){
                                    queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(1), -1, getPropertyChoice());
                                    for(int j=0; j<fileChOp.size(); j++){
                                        queries[Integer.valueOf(fileChOp.get(j))]="";
                                    i=0;}
                                    oneUp--;
                        }
                }
                    
            }
            fileChOp.clear();                    
            oneUp=3;
            //forth wave queries - third file => αν στο τρίτο αρχείο υπάρχουν αλλαγές
        System.out.println("---------------------3----------------------");
            queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(2), -1, getPropertyChoice());
            for(int i=0; i<queries.length; i++){
                    if(queries[i].length()<1 || oneUp==0) //alliws exception
                           break;//failsafe
                    rs.add(fetchResults(queries[i]));
                    if(!rs.isEmpty()){
                        setChangeOperator(""+i);
                        //results.addAll(3,getNodes(rs));
                        ArrayList<ArrayList<ArrayList<String>>> temp1 = getNodes(rs);
                        for(int k=0; k<temp1.size(); k++){
                                fileChOp.add(changeOperatorChoice);
                                results.add(temp1.get(k));
                        }
                    }
                    rs.clear();
                    
                    if(getChangeOperatorChoice().equals("RENAME_CLASS")
                            ||getChangeOperatorChoice().equals("RENAME_PROPERTY")){
                                queries = QueryConstructor.queryMultiBuilder(OntoConstants.urlz.get(2), -1, getPropertyChoice());
                                for(int j=0; j<fileChOp.size(); j++){
                                        queries[Integer.valueOf(fileChOp.get(j))]="";
                                i=0;}
                                oneUp--;
                    }
                    
            }
            
        return results;
    }
    
//    private static void exeSparql(String stringQuery) { //debuging purpuse
//        Query query = QueryFactory.create(stringQuery);
//            QueryExecution qExe = QueryExecutionFactory.sparqlService(OntoConstants.endpointURL, query);
//            ResultSet results = qExe.execSelect();
//
//            JSONObject resultJson = resultSetToJson(results);
//            System.out.println(resultJson.toString());
//            JSONArray arrya = resultJson.getJSONObject("results").getJSONArray("bindings");
//            for(int i=0; i<arrya.length(); i++){
//                System.out.println("s: " + arrya.getJSONObject(i).getJSONObject("s").get("value")); // general results
//                //System.out.println("p: " + arrya.getJSONObject(i).getJSONObject("property").get("value"));
//                //System.out.println("ss: " + arrya.getJSONObject(i).get("ss")); // general results
//                //System.out.println("from w: " + arrya.getJSONObject(i).getJSONObject("w"));
//                //System.out.println("to x: " + arrya.getJSONObject(i).getJSONObject("x"));
//                //System.out.println("o: " + arrya.getJSONObject(i).getJSONObject("o").get("value"));
//                System.out.println("from: " + arrya.getJSONObject(i).getJSONObject("v").get("value"));
//                System.out.println("to: " + arrya.getJSONObject(i).getJSONObject("vv").get("value"));
//               // System.out.println("<----------------------------->");
//            }
//    }

    private static ResultSet fetchResults(String queryString) {
        System.out.println("Q:"+queryString);
        Query query = QueryFactory.create(queryString);
        QueryExecution qExe = QueryExecutionFactory.sparqlService(OntoConstants.endpointURL, query);
        ResultSet resultSet = qExe.execSelect();

        return resultSet;
    }

//    private static JSONObject resultSetToJson(ResultSet resultSet) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ResultSetFormatter.outputAsJSON(outputStream, resultSet);
//        String jsonString = new String(outputStream.toByteArray());
//        JSONObject json = new JSONObject(jsonString);
//        return json;
//    }
    
    public void setChoices(String changeOperatorChoice, String propertyChoice) {
        this.changeOperatorChoice = changeOperatorChoice;
        this.propertyChoice = propertyChoice;
    }
    private void setProperty(String newProperty) {
        this.propertyChoice = newProperty.replace("http://cidoc#", "");
    }
    private void setChangeOperator(String newChangeOperator) {        
        this.changeOperatorChoice = newChangeOperator;
    }
    private static String getChangeOperatorChoice(){
        //if(changeOperatorChoice.equals("-1"))    //debuging
        //    return "?x";
        //else
            return OntoConstants.changeOperators.get(Integer.valueOf(changeOperatorChoice)); // ειναι uri
    }
    private static String getPropertyChoice(){
        //if(propertyChoice.equals("")){
            //den 8a mpainei edw. return "?o";//debuging only
       /// }
        //else
            return "'http://cidoc#"+propertyChoice+"'"; //είναι literal όχι uri
    }
    
    private static ArrayList<ArrayList<String>> getNodes1(ResultSet results){ //1 extra URI
        ArrayList<ArrayList<String>> nodes = new ArrayList<>();
         
            while (results.hasNext()){//καλύπτει την περίπτωση πολλαπλών εγγραφών για 1 (chOp AND prop)
                ArrayList<String> temp = new ArrayList<>();
                QuerySolution result = results.nextSolution();
                temp.add(getChangeOperatorChoice());
                temp.add(getPropertyChoice()); //?o              
                temp.add(result.get("oo").toString());
                temp.add(result.get("v").toString());
                temp.add(result.get("vv").toString());
                nodes.add(temp);            

           
           }
        
        return nodes;
    }
    private ArrayList<ArrayList<String>> getNodes2(ResultSet results){ //two extra URI
        ArrayList<ArrayList<String>> nodes = new ArrayList<>();
        
            while (results.hasNext()){ //καλύπτει την περίπτωση πολλαπλών εγγραφών για 1 (chOp AND prop)
                ArrayList<String> temp = new ArrayList<>();
                QuerySolution result = results.nextSolution();
                temp.add(getChangeOperatorChoice());
                temp.add(getPropertyChoice()); //?o
                temp.add(result.get("oo").toString());             
                temp.add(result.get("ooo").toString());
                temp.add(result.get("v").toString());
                temp.add(result.get("vv").toString());
                nodes.add(temp);            

           }
        return nodes;
    }
    private ArrayList<ArrayList<String>> getNodes0(ResultSet results){ //zero extra URI
        ArrayList<ArrayList<String>> nodes = new ArrayList<>();
      
            while (results.hasNext()){ //καλύπτει την περίπτωση πολλαπλών εγγραφών για 1 (chOp AND prop)
                ArrayList<String> temp = new ArrayList<>();
                QuerySolution result = results.nextSolution();
                temp.add(getChangeOperatorChoice());
                temp.add(getPropertyChoice()); //?o
                temp.add(result.get("v").toString());
                temp.add(result.get("vv").toString());
                nodes.add(temp);            

           }
        return nodes;
    }
         private ArrayList<ArrayList<String>> getNodesSpecial(ResultSet results){ //zero extra URI
        ArrayList<ArrayList<String>> nodes = new ArrayList<>();
          
            while (results.hasNext()){ //καλύπτει την περίπτωση πολλαπλών εγγραφών για 1 (chOp AND prop)
                ArrayList<String> temp = new ArrayList<>();
                QuerySolution result = results.nextSolution();
                temp.add(getChangeOperatorChoice());
                temp.add(getPropertyChoice()); //?o
                temp.add(result.get("oo").toString());            
                setProperty(result.get("oo").toString());   
                temp.add(result.get("v").toString());
                temp.add(result.get("vv").toString());
                nodes.add(temp);
           }
        return nodes;
    }
    private ArrayList<ArrayList<ArrayList<String>>> getNodes(ArrayList<ResultSet> results){        
      ArrayList<ArrayList<ArrayList<String>>> nodes = new ArrayList<>(); //πολλαπλα αρχει AND καλύπτει την περίπτωση πολλαπλών εγγραφών για 1 (chOp AND prop)
          for(int i=0; i<results.size(); i++){
            //System.out.println("In getNodes: "+getChangeOperatorChoice()+" "+getPropertyChoice());
          try{
          if(results.get(i).hasNext()){          
            if(OntoConstants.changeOperators.get(0).equals(getChangeOperatorChoice())){
                nodes.add(getNodes2(results.get(i)));
                continue;
            }
            if(OntoConstants.changeOperators.get(1).equals(getChangeOperatorChoice())){
                nodes.add(getNodes2(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(2).equals(getChangeOperatorChoice())){
                nodes.add(getNodes2(results.get(i)));     
                continue;           
            }        
            if(OntoConstants.changeOperators.get(3).equals(getChangeOperatorChoice())){
                nodes.add(getNodes2(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(4).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }
            if(OntoConstants.changeOperators.get(5).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(6).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(7).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(8).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(9).equals(getChangeOperatorChoice())){
                nodes.add(getNodes2(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(10).equals(getChangeOperatorChoice())){
                nodes.add(getNodes0(results.get(i)));
                continue;
            }        
            if(OntoConstants.changeOperators.get(11).equals(getChangeOperatorChoice())){
                nodes.add(getNodes1(results.get(i)));
                continue;
            }
            if(OntoConstants.changeOperators.get(12).equals(getChangeOperatorChoice())){
                nodes.add(getNodesSpecial(results.get(i)));
                continue;
            }         
            if(OntoConstants.changeOperators.get(13).equals(getChangeOperatorChoice())){
                nodes.add(getNodesSpecial(results.get(i)));
            }               
          }}catch(Exception e){}
        }
       return nodes;
    }
}



/*
    private static String queryBuilder(String urlz, String getChangeOperatorChoice, String getPropertyChoice){        
        return "PREFIX typical:<http://www.semanticweb.orgontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first q START
                                + "?s "
                                + getChangeOperatorChoice   // ?x --> uri
                                + " "+getPropertyChoice+". " // ?o --> property literal
                                //first q END               
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
    }


private static void queryKnownChangeOperatorNotProperty() {
       String stringQuery;
    }
    
    private static void queryKnownPropertyNotChangeOperator() {
       String stringQuery;
       
       for(int i=0; i<urlz.size(); i++){
           for(int j=0; j<changeOperators.size(); j++){
                stringQuery =  QueryConstructor.queryBuilder(urlz.get(i),changeOperators.get(j),getPropertyChoice());
                System.out.println(stringQuery);
                exeSparql(stringQuery); //debuging
           }
        }
    }
    private static void queryKnownPropertyAndChangeOperator() {
       String stringQuery;
       
       for(int i=0; i<urlz.size(); i++){
             stringQuery =  QueryConstructor.queryBuilder(urlz.get(i),getChangeOperatorChoice(),getPropertyChoice());
            System.out.println(stringQuery);
            exeSparql(stringQuery); //debuging
        }
    }
*/    