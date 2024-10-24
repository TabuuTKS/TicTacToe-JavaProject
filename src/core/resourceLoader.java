/* Tic Tac Toe Game Application
 * The resourceLoaader Class is responsable for loading resources from assets folder.
*/

package core;

public class resourceLoader {
    static String projectPath = System.getProperty("user.dir");

    public static String getAsset(String path) {
        return projectPath+"/assets/"+path;
    }

    public static void setProjectPath(String path) {
        projectPath = path;
    }
}
