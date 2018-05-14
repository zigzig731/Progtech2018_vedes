package hu.inf.unideb.td;

import hu.inf.unideb.td.controller.ImputHandler;
import hu.inf.unideb.td.model.*;
import hu.inf.unideb.td.model.GameObject;
import hu.inf.unideb.td.model.gameObjects.Enemy;
import hu.inf.unideb.td.model.gameObjects.Tower;
import hu.inf.unideb.td.model.SessionManagement.Session;
import hu.inf.unideb.td.model.gameObjects.enemies.Enemy_Runner;
import hu.inf.unideb.td.model.gameObjects.enemies.Enemy_Slow;
import hu.inf.unideb.td.model.gameObjects.towers.AlapTorony;
import hu.inf.unideb.td.model.managers.GameObjectManager;
import hu.inf.unideb.td.model.managers.LightManager;
import hu.inf.unideb.td.model.managers.MaterialManager;
import hu.inf.unideb.td.model.player.MousePicker;
import hu.inf.unideb.td.model.mapElements.Path;
import hu.inf.unideb.td.model.player.Camera;
import hu.inf.unideb.td.model.utility.Loader;
import hu.inf.unideb.td.view.Renderer;
import org.joml.Vector3f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    public static long window;

    public void run() {
        init();
        loop();
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        // glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        // glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        //  glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(1280, 720, "Tower Defense", NULL, NULL);
        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

    }

    private void loop() {

        GL.createCapabilities();
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_DEPTH_TEST);
        Loader loader = new Loader();
        StaticShader shader=new StaticShader();
        Renderer renderer=new Renderer(shader);
        Camera camera=new Camera();
        Path.addWaypoint(new Vector3f(6,1,-2));
        Path.addWaypoint(new Vector3f(6,1,-10));
        Path.addWaypoint(new Vector3f(14,1,-10));
        Path.addWaypoint(new Vector3f(14,1,-6));
        Path.addWaypoint(new Vector3f(22,1,-6));
        Path.addWaypoint(new Vector3f(22,1,-26));
        Path.addWaypoint(new Vector3f(4,1,-26));
        Path.addWaypoint(new Vector3f(4,1,-34));
        Path.addWaypoint(new Vector3f(12,1,-34));
        Path.addWaypoint(new Vector3f(12,1,-42));
        Path.addWaypoint(new Vector3f(38,1,-42));
        Path.addWaypoint(new Vector3f(38,1,-14));
        Path.addWaypoint(new Vector3f(50,1,-14));
        Path.addWaypoint(new Vector3f(70,9,-14));
        Path.addWaypoint(new Vector3f(78,9,-14));
        Path.addWaypoint(new Vector3f(78,9,-6));
        Path.addWaypoint(new Vector3f(94,9,-6));
        Path.addWaypoint(new Vector3f(94,9,-22));
        Path.addWaypoint(new Vector3f(94,9,-22));

        //=====================================================================================================
        Texture waypointDiffuse = new Texture(loader.loadTexture("Textures/red.png"));
        SetupScene.setup();

        MaterialManager.get("metal_waypoint").setDiffuse(waypointDiffuse);
        Entity turret=new Entity("metal_base","muzzleflash", new Vector3f(0,2f,4),new Vector3f(0,20,0),1f);
        Entity turreta=new Entity("metal_base","enemyHR", new Vector3f(0,2f,-10),new Vector3f(0,20,0),1f);
        Entity turretgun=new Entity("metal_base","towergun", new Vector3f(0,-0.5f,-10),new Vector3f(0,90,0),1f);
        Entity waypointEntity=new Entity("metal_waypoint","waypoint", new Vector3f(0,0f,0),new Vector3f(0,0,0),1f);
        Entity terrain=new Entity("metal_base","terrainHR", new Vector3f(0,0,-15),new Vector3f(0,-90,0),0.5f);
        Entity plane=new Entity("forest_base","plane", new Vector3f(0,0,0),new Vector3f(0,0,0),0.5f);
        Entity road=new Entity("Asphalt_base","terrain_road", new Vector3f(0,0,0),new Vector3f( 0,0,0),0.5f);
        Entity water=new Entity("water_base","water", new Vector3f(0,0,0),new Vector3f(0,0,0),0.5f);
        Entity building=new Entity("metal_light_base","mapbuildings", new Vector3f(0,0,0),new Vector3f(0,0,0),0.5f);
        Entity gridunit=new Entity("healthbar_base","gridunit", new Vector3f(0,2,0),new Vector3f(0,0,0),1f);
        //=====================================================================================================
        glClearColor(0.7f, 0.7f, 1f, 0.0f);
        glEnable(GL_NORMALIZE);
        Enemy bloodbunny = new Enemy();
        List<Enemy> enemies=new ArrayList<Enemy>();
        enemies.add(bloodbunny);
        float lasttime=0;

        Session session = loader.loadSession("Session1");
      //  System.out.println(session.getWaves().get(1).getWaveComponents().get(1).getType());
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);

        MousePicker picker = new MousePicker(camera,renderer.getProjectionmatrix());

        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            ImputHandler.processInput(window, picker);

            renderer.prepare();

            shader.start();

            shader.loadLights(LightManager.getLights());
            shader.loadViewMatrix(camera);
            shader.loadTexture();
            renderer.render(plane);
            renderer.render(road);
            renderer.render(water);
            renderer.render(building);
            renderer.render(gridunit);
            renderer.render(turret);

            for(GameObject enemy:GameObjectManager.gameObjects)
            {
                enemy.display(renderer);
                enemy.update();
            }

            Path.displayPath(renderer,waypointEntity);
            shader.stop();

            if(glfwGetTime()-lasttime>1&&GameObjectManager.gameObjects.size()<30) {
                if(GameObjectManager.gameObjects.size() % 2==0){
                  GameObjectManager.add(new Enemy_Slow());
                } else GameObjectManager.add(new Enemy_Runner());
                lasttime=(float)glfwGetTime();
            }

            glfwSwapBuffers(window);
            glfwPollEvents();
            picker.update();
            gridunit.setPosition(new Vector3f((int)(((float)picker.get3DPoint().x)/4.0f)*4,2,(int)(((float)picker.get3DPoint().z)/4f)*4));
            GameObjectManager.clean();
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

}