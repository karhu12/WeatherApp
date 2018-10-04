/****************************************
 * Weather Program
 * 
 * Fetches weather from wanted city using openweathermap API (https://openweathermap.org)
 * HTML done by using the java.net library
 * Request parsed using JSON library called GSON
 * 
*/

import networking.*;
import ui.*;
import data.*;
import java.util.*;
import java.io.*;

/* Libs */
import com.google.gson.*;

public final class App {
    public static void main(String[] args) throws Exception {
        Ui.launchUi(args);
    }
}
