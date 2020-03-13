/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologyevolutionvisualizer.virtuososervice;

import ontologyevolutionvisualizer.OntoConstants;

/**
 *
 * @author lefterisLapdance
 * 
 * "RENAME_CLASS", "CHANGE_TO_DATATYPE_PROPERTY", "GENERALIZE_RANGE",
            "GENERALIZE_DOMAIN", "RENAME_PROPERTY", "ADD_SUPERCLASS_TO_CLASS",
            "DELETE_SUPERPROPERTY","DELETE_SUPERCLASS_FROM_CLASS", "ADD_SUPERPROPERTY",
            "ADD_PROPERTY","DELETE_PROPERTY", "ADD_CLASS", "DELETE_CLASS", "PULL_DOWN_CLASS"
 */
public class QueryConstructor {
    
   public QueryConstructor(){
    
   }
   
   //PULL_DOWN_CLASS
   private static String sqarqlPullDownClass(String urlz, String property){
        return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:PULL_DOWN_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                                                              
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                 
                                //third triple START
                                + "?s "
                                + "typical:URI3"   // ?x --> uri
                                + " ?ooo. " // ?o --> property literal
                                //third triple END             
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   
   //DELETE_CLASS
   private static String sqarqlDeleteClass(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:DELETE_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END               
                                //secondary triple START
                                + "?s "
                                + "typical:has_superclass"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                  
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
    
   //ADD_CLASS
   private static String sqarqlAddClass(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:ADD_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                
                                //secondary triple START
                                + "?s "
                                + "typical:has_superclass"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //DELETE_PROPERTY
   private static String sqarqlDeleteProperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:DELETE_PROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END               
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //ADD_PROPERTY 
   private static String sqarqlAddProperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:ADD_PROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                
                                //secondary triple START
                                + "?s "
                                + "typical:Domain"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                 
                                //third triple START
                                + "?s "
                                + "typical:Range"   // ?x --> uri
                                + " ?ooo. " // ?o --> property literal
                                //third triple END                
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //ADD_SUPERPROPERTY 
   private static String sqarqlAddSuperproperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:ADD_SUPERPROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //DELETE_SUPERCLASS_FROM_CLASS 
   private static String sqarqlDeleteSuperclass(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:DELETE_SUPERCLASS_FROM_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //DELETE_SUPERPROPERTY 
   private static String sqarqlDeleteSuperproperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:DELETE_SUPERPROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //ADD_SUPERCLASS_TO_CLASS
   private static String sqarqlAddSuperclass(String urlz, String property){
        return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:ADD_SUPERCLASS_TO_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                 
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END               
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //RENAME_PROPERTY
   private static String sqarqlRenameProperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:RENAME_PROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END               
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //GENERALIZE_DOMAIN  
   private static String sqarqlGeneralizeDomain(String urlz, String property){
      return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:GENERALIZE_DOMAIN"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                  
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                 
                                //third triple START
                                + "?s "
                                + "typical:URI3"   // ?x --> uri
                                + " ?ooo. " // ?o --> property literal
                                //third triple END              
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }     
   //"RENAME_CLASS"
   private static String sqarqlRenameClass(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:RENAME_CLASS"
                                + " "+property+". " // ?o --> property literal
                                //first triple END               
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END               
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }
   //CHANGE_TO_DATATYPE_PROPERTY
   private static String sqarqlChangeDatatypeProperty(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:CHANGE_TO_DATATYPE_PROPERTY"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                    
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                 
                                //third triple START
                                + "?s "
                                + "typical:URI3"   // ?x --> uri
                                + " ?ooo. " // ?o --> property literal
                                //third triple END              
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }      
   //GENERALIZE_RANGE
   private static String sqarqlGeneralizeRange(String urlz, String property){
       return "PREFIX typical:<http://www.semanticweb.org/alucard/ontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first triple START
                                + "?s "
                                + "typical:GENERALIZE_RANGE"
                                + " "+property+". " // ?o --> property literal
                                //first triple END                   
                                //secondary triple START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary triple END                 
                                //third triple START
                                + "?s "
                                + "typical:URI3"   // ?x --> uri
                                + " ?ooo. " // ?o --> property literal
                                //third triple END                 
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
   }
   //generalized - mostly for debugging
   public static String queryMultiBuilder(String urlz, String checkChangeOperatorChoice, String checkPropertyChoice){       
                /**
              *
              * @author lefterisLapdance
              * 
              * "RENAME_CLASS", "CHANGE_TO_DATATYPE_PROPERTY", "GENERALIZE_RANGE",
                         "GENERALIZE_DOMAIN", "RENAME_PROPERTY", "ADD_SUPERCLASS_TO_CLASS",
                         "DELETE_SUPERPROPERTY","DELETE_SUPERCLASS_FROM_CLASS", "ADD_SUPERPROPERTY",
                         "ADD_PROPERTY","DELETE_PROPERTY", "ADD_CLASS", "DELETE_CLASS", "PULL_DOWN_CLASS"
              */
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(12))){
                return sqarqlRenameClass(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(1))){
                return sqarqlChangeDatatypeProperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(2))){
                return sqarqlGeneralizeRange(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(3))){
                return sqarqlGeneralizeDomain(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(13))){
                return sqarqlRenameProperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(5))){
                return sqarqlAddSuperclass(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(6))){
                return sqarqlDeleteSuperproperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(7))){
                return sqarqlDeleteSuperclass(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(8))){
                return sqarqlAddSuperproperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(9))){
                return sqarqlAddProperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(10))){
                return sqarqlDeleteProperty(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(11))){
                return sqarqlAddClass(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(4))){
                return sqarqlDeleteClass(urlz,checkPropertyChoice);
        }        
        if(checkChangeOperatorChoice.equals(OntoConstants.changeOperators.get(0))){
                return sqarqlPullDownClass(urlz,checkPropertyChoice);
        }
       return "";   //never         
   }
   public static String[] queryMultiBuilder(String urlz, int changeOperatorChoice, String checkPropertyChoice){
        String[] result = new String[OntoConstants.changeOperators.size()];
        String previousChangeOperatorChoice;
        
        if(changeOperatorChoice<0){
            previousChangeOperatorChoice = ""; //για να παράξει όλα τα queries
        }
        else{
               previousChangeOperatorChoice = OntoConstants.changeOperators.get(changeOperatorChoice);
               result[changeOperatorChoice] = "";
                                              //για να κόψει το προηγούμενο
        }             
            if(!OntoConstants.changeOperators.get(0).equals(previousChangeOperatorChoice)){
                    result[0]=sqarqlPullDownClass(urlz,checkPropertyChoice);
            }
            if(!OntoConstants.changeOperators.get(1).equals(previousChangeOperatorChoice)){
                    result[1]=sqarqlChangeDatatypeProperty(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(2).equals(previousChangeOperatorChoice)){
                    result[2]=sqarqlGeneralizeRange(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(3).equals(previousChangeOperatorChoice)){
                    result[3]=sqarqlGeneralizeDomain(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(4).equals(previousChangeOperatorChoice)){
                    result[4]=sqarqlDeleteClass(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(5).equals(previousChangeOperatorChoice)){
                    result[5]=sqarqlAddSuperclass(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(6).equals(previousChangeOperatorChoice)){
                    result[6]=sqarqlDeleteSuperproperty(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(7).equals(previousChangeOperatorChoice)){
                    result[7]=sqarqlDeleteSuperclass(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(8).equals(previousChangeOperatorChoice)){
                    result[8]=sqarqlAddSuperproperty(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(9).equals(previousChangeOperatorChoice)){
                    result[9]=sqarqlAddProperty(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(10).equals(previousChangeOperatorChoice)){
                    result[10]=sqarqlDeleteProperty(urlz,checkPropertyChoice);
            }        
            if(!OntoConstants.changeOperators.get(11).equals(previousChangeOperatorChoice)){
                    result[11]=sqarqlAddClass(urlz,checkPropertyChoice);
            }
            if(!OntoConstants.changeOperators.get(12).equals(previousChangeOperatorChoice)){
                    result[12]=sqarqlRenameClass(urlz,checkPropertyChoice);
            }           
            if(!OntoConstants.changeOperators.get(13).equals(previousChangeOperatorChoice)){
                    result[13]=sqarqlRenameProperty(urlz,checkPropertyChoice);
            }        
        
        return result;                
   }
   
   public static String queryBuilder(String urlz, String checkChangeOperatorChoice, String checkPropertyChoice){        //debuging mainly
       return "PREFIX typical:<http://www.semanticweb.orgontologies/2017/0/untitled-ontology-10#>"
                            + " PREFIX owl:<http://www.w3.org/2002/07/owl#>"
                            + " PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>"
                            + " SELECT DISTINCT *"
                            + " FROM " + urlz
                            + " WHERE {"
                                //first q START
                                + "?s "
                                + checkChangeOperatorChoice   // ?x --> uri
                                + " "+checkPropertyChoice+". " // ?o --> property literal
                                //first q END               
                                //secondary q START
                                + "?s "
                                + "typical:URI2"   // ?x --> uri
                                + " ?oo. " // ?o --> property literal
                                //secondary q END
                                + "?s typical:from_version ?v. "
                                + "?s typical:to_version ?vv"
                             + " }"
                             ;
    }    
}