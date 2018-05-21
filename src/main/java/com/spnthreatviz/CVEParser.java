package com.spnthreatviz;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

/*
 * This class is based on https://stackoverflow.com/questions/11428329/
 * Refer to https://stackoverflow.com/questions/23070298 for custom deserialization (originally planned, but CVE data is too nested for this to be a functional option)
 */

/** 
 * The CVEParser class parses the NVD CVE files into POJOs and should be utilized when setting up the database.
 */
public class CVEParser {

    /**
     * Constructor for a CVEParser. Does nothing.
     */
    public CVEParser() {}

    /**
     * Isolates the CVE_Items array from input JsonElement for later parsing.
     * @param input the JSON string to use as input.
     * @exception STVException.CVEParsingException on missing CVE_Items field in provided JSON input.
     */
    public JsonArray getCVEItems(String input) throws STVException.CVEParsingException {
        JsonParser parser = new JsonParser();
        JsonObject cveRootObj = parser.parse(input).getAsJsonObject(); //Turn input into a JsonObject
        JsonElement parsedCVEItems = cveRootObj.get("CVE_Items");
        if (parsedCVEItems == null) {
            throw new STVException.CVEParsingException("getCVEItems: input missing CVE_Items field.", new Throwable());
        }
        return parsedCVEItems.getAsJsonArray();
    }

    /**
     * Iterates over the elements of the CVE_Items JsonArray and creates CVEObjects to be added to the database.
     * @param cveItems the JsonArray of cveItems to be parsed and added to the database.
     * @exception STVException.CVEParsingException on missing fields in provided JSON input.
     */
    public void getCVEItems(JsonArray cveItems) throws STVException.CVEParsingException  {
        for (JsonElement cveElement : cveItems) {
            JsonObject cve = cveElement.getAsJsonObject(); //convert array element to JsonObject
            CVEObject cveObj = parseCVE(cve); //parse to a POJO
            //Handle Database entry
        }
    }

    /**
     * Handles parsing the important bits of a JsonObject into a meaningful CVEObject.
     * @param cveItem the JsonObject to parse and add to a CVEObject instance.
     * @exception STVException.CVEParsingException on missing fields in provided JSON input.
     */
    public CVEObject parseCVE(JsonObject cveItem) throws STVException.CVEParsingException  {
        CVEObject toReturn = new CVEObject();

        //One by one, obtain the important fields.


        return toReturn;
    }

}