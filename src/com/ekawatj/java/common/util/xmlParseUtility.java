/**
 * Created by ejirapongpan on 12/9/15.
 */
package com.ekawatj.java.common.util;

import org.jdom.Element;
import java.util.Properties;
import java.util.Vector;

public class xmlParseUtility {

  public static class XMLElement extends Element implements java.io.Serializable  {

    public XMLElement(String local) {
      super(local);
    }

  }

  /**
   *  Converts traceVariables from CustomException into XMLElement.
   *  @param se       CustomException object that contains traceVariables.
   *  @param varName  an attribute name on the top level XMLElement object
   *  return the result XMLElement object
   */
  public static XMLElement getXmlElement(Exception se, String varName) {
    XMLElement seElement = null;
    XMLElement xmlElement = null;
    StackTraceElement[] stackTrace = se.getStackTrace();
    String javaStackTrace = se.getLocalizedMessage();
    Vector commentVector = new Vector();
    Properties trackedVariables = new Properties();

    try {
      seElement = new XMLElement("CustomException");
      seElement.setAttribute("hashCode",se.hashCode()+"");
      if (varName == null) {
        seElement.setAttribute("varName","");
      } else {
        seElement.setAttribute("varName",varName);
      }

      seElement.addContent((new XMLElement("Date")).addContent((new java.util.Date()).toString()));
      seElement.addContent((new XMLElement("Message")).addContent(se.getMessage()));

      xmlElement = new XMLElement("Custom-Stack-Trace");
      seElement.addContent(xmlElement);
      if (stackTrace != null) {
        for (int i=0; i<stackTrace.size(); i++) {
          xmlElement.addContent((new XMLElement("Trace")).addContent((String) stackTrace.elementAt(i)));
        }
      }

      seElement.addContent((new XMLElement("Java-Stack-Trace")).addContent(javaStackTrace));

      xmlElement = new XMLElement("Extra-Comments");
      seElement.addContent(xmlElement);
      if (commentVector != null) {
        for (int i=0; i<commentVector.size(); i++) {
          xmlElement.addContent((new XMLElement("Comment")).addContent((String) commentVector.elementAt(i)));
        }
      }

      seElement.addContent(getXmlElement(trackedVariables, "trackedVariables"));
    } catch (Exception e) {
      log.error("Failed to print XML output for SuperException instance.", e);
    }

    return(seElement);
  }

}
