/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontologyevolutionvisualizer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lefterisLapdance
 */
public interface OntoConstants {
    public static final ArrayList<String> changeOperators = new ArrayList<>(Arrays.asList(
            "PULL_DOWN_CLASS", "CHANGE_TO_DATATYPE_PROPERTY", "GENERALIZE_RANGE",
            "GENERALIZE_DOMAIN","DELETE_CLASS", "ADD_SUPERCLASS_TO_CLASS",
            "DELETE_SUPERPROPERTY","DELETE_SUPERCLASS_FROM_CLASS", "ADD_SUPERPROPERTY",
            "ADD_PROPERTY","DELETE_PROPERTY", "ADD_CLASS",
            "RENAME_CLASS", "RENAME_PROPERTY"
    ));
    public static final String endpointURL = "http://10.0.29.238:8890/sparql"; // URL        
    public static final ArrayList<String> urlz = new ArrayList<>(Arrays.asList(
            "<http://dev.eclipsys.gr/virtuoso/v3.2.1-v3.3.2.owl> ",
            "<http://dev.eclipsys.gr/virtuoso/v3.3.2-v3.4.9.owl>",
            "<http://dev.eclipsys.gr/virtuoso/v3.4.9-v4.2.owl>"));
    
    public static final String GRAPH = "graph";
    public static final String NODES = "graph.nodes";
    public static final String EDGES = "graph.edges";
    public static final String LABEL = "label";
}
