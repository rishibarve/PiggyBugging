package com.piggybugging;

import com.piggybugging.images.Image;
import com.piggybugging.pojo.Coordinate;

import java.util.HashSet;

public class CollisionRegistry {

    private final HashSet<Image> images;

    public CollisionRegistry(){
        images = new HashSet<>();
    }

    public boolean registerImage(Image image){
        images.add(image);
        return true;
    }

    public Image isColliding(Image image){
        for(Image image1 : images){

            Coordinate c = image.getCoordinate();
            Coordinate c1 = image1.getCoordinate();

            Coordinate center = new Coordinate(c.getX() + image.getWidth()/2, c.getY() + image.getHeight()/2);
            int r1 = image.getWidth()/2;
            Coordinate center1 = new Coordinate(c1.getX() + image1.getWidth()/2, c1.getY() + image1.getHeight()/2);

            int r2 = image.getWidth()/2;

            if (Math.pow(center.getX() - center1.getX(), 2) + Math.pow(center.getY() - center1.getY(), 2) < Math.pow(r1 + r2 , 2)){
                return image1;
            }

        }
        return null;

    }

    public boolean unregisterImage(Image image){
        boolean wasPresent = images.remove(image);
        if(wasPresent){
            System.out.println("Image removed from the collision registry.");
        }else {
            System.out.println("Image was not present in the collision registry.");
        }
        return wasPresent;
    }

}
