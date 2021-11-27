package com.piggybugging;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.piggybugging.background.Background;
import com.piggybugging.background.Lane;
import com.piggybugging.images.Bug;
import com.piggybugging.images.Cactus;
import com.piggybugging.images.Elephant;
import com.piggybugging.images.Image;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GameScreen implements Screen {

    // screen
    Camera camera;
    Viewport viewport;

    // graphics
    SpriteBatch batch;
    Background background;


    Bug bug;
    ArrayList<Lane> lanes = new ArrayList<>(3);
    HashSet<Image> elephantHashSet = new HashSet<>();
    Configuration configuration;
    int timeCounter;

    // world parameters
    private final int WORLD_WIDTH = 128;
    private final int WORLD_HEIGHT = 72;

    private CollisionRegistry collisionRegistry;

    public GameScreen(){
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        batch = new SpriteBatch();
        this.collisionRegistry = new CollisionRegistry();

        background = new Background();
        bug = new Bug(20, 120);
        configuration = Configuration.getInstance();
        timeCounter = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        ScreenUtils.clear(0.53f,0.81f,0.92f, 1);
        background.drawBackground(batch);
        bug.drawAnimatedObject(batch);
        for(Image elephant : elephantHashSet){
//			if(cactus.getCoordinate().getX() + cactus.getWidth() < 0 ){
//				cactusList.remove(cactus);
//			}
            elephant.drawImage(batch);
        }
        if(!Configuration.getInstance().isDead()){
            if (Gdx.input.isKeyJustPressed(19) || (Gdx.input.isTouched()&&Gdx.input.getDeltaY()>0)) { //UP
                bug.moveUp(100);
            }
            if (Gdx.input.isKeyJustPressed(20) || (Gdx.input.isTouched()&&Gdx.input.getDeltaY()<0)) { //Down
                bug.moveDown(100);
            }
            if (Gdx.input.isKeyPressed(21)) { //LEFT
                bug.moveBackward();
            }
            if (Gdx.input.isKeyPressed(22)) { //RIGHT
                bug.moveForward();
            }
            if (Gdx.input.isKeyJustPressed(62) && !bug.isInAir()) { // SPACE
                bug.jump();
            }

            if (timeCounter % 10 == 0){
                Image bugCollidingWith = collisionRegistry.isColliding(bug);
                if(bug.isInAir()){
                    if(bugCollidingWith instanceof Elephant){ //this condition also checks non null

                        System.out.println("controlling this animal !! ");
                        collisionRegistry.unregisterImage(bugCollidingWith);
                        bug.controlAnimal((Elephant) bugCollidingWith);
                    } else if(bugCollidingWith instanceof Cactus){
                        System.out.println("flying bug collides with cactus");
                        configuration.setDead(true);
                    }

                }

                else if(!bug.isInAir()){

                    if(bugCollidingWith != null){
                        System.out.println("bug collided");
                        configuration.setDead(true);
                    }
                    Image controlledAnimalCollidingWith = null;
                    controlledAnimalCollidingWith = collisionRegistry.isColliding(bug.getControlledAnimal());
                    if (controlledAnimalCollidingWith != null){
                        System.out.println("controlled animal collided");
                        configuration.setDead(true);
                    }
                }

            }



            if (timeCounter % 100 == 0 && timeCounter % 200 == 0) {
                Random rand = new Random();
                List<Integer> l = new ArrayList<>(3);
                l.add(20);
                l.add(120);
                l.add(220);
                int y = l.get(rand.nextInt(l.size()));
                Elephant elephant = new Elephant(Gdx.graphics.getWidth(), y, false);
                elephant.setMoveWithBackground(true);
                this.collisionRegistry.registerImage(elephant);
                elephantHashSet.add(elephant);
            }
            if (timeCounter % 100 == 0 && timeCounter % 200 != 0){
                Random rand = new Random();
                List<Integer> l = new ArrayList<>(3);
                l.add(20);
                l.add(120);
                l.add(220);
                int y = l.get(rand.nextInt(l.size()));
                Cactus cactus = new Cactus(Gdx.graphics.getWidth(), y, false);
                cactus.setMoveWithBackground(true);
                this.collisionRegistry.registerImage(cactus);
                elephantHashSet.add(cactus);
            }
        }
        timeCounter++;
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
//        viewport.update(width, height, true);
//        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();
    }


}
