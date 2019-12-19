/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.codecuisine.asciination.engine.io;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import java.io.File;
import javafx.scene.shape.TriangleMesh;

/**
 *
 * @author kenne
 */
public class STLParser {

    public static String PLAYER_SHIP = "models/ships/ship_0.stl";

    public static TriangleMesh getMesh(String meshPath) {
        StlMeshImporter stlImporter = new StlMeshImporter();
        TriangleMesh mesh = null;
        try {
            File file = new File(stlImporter.getClass().getClassLoader().getResource(meshPath).getFile()
            );
            stlImporter.read(file);
            mesh = stlImporter.getImport();
            stlImporter.close();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            return mesh;
        }
    }
}
