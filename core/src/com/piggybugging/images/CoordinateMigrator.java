package com.piggybugging.images;

import com.piggybugging.Configuration;
import com.piggybugging.constants.Direction;
import com.piggybugging.pojo.Coordinate;

public class CoordinateMigrator {

    int migrationSpeed;
    boolean isMigrating;
    Direction migrationDirection;
    int migratedDistance;
    int migrationDistance;

    public CoordinateMigrator(){
        migrationSpeed = 4;
        isMigrating = false;
        migratedDistance = 0;
    }

    public boolean moveUp(int units){
        if (isMigrating){
            System.out.println("already migrating. can't override current operation.");
            return false;
        }
        migrationDistance = units;
        migrationDirection = Direction.UP;
        migratedDistance = 0;
        isMigrating = true;
        return true;
    }

    public boolean moveDown(int units){
        if (isMigrating){
            System.out.println("already migrating. can't override current operation.");
            return false;
        }
        migrationDistance = units;
        migrationDirection = Direction.DOWN;
        migratedDistance = 0;
        isMigrating = true;
        return true;
    }

    public Coordinate migrate(Coordinate coordinate){
        if(!Configuration.getInstance().isDead() && isMigrating && migrationDirection.equals(Direction.UP)){
            coordinate.setY(coordinate.getY() + migrationSpeed);
            migratedDistance +=migrationSpeed;
        }
        else if(!Configuration.getInstance().isDead() && isMigrating && migrationDirection.equals(Direction.DOWN)){
            coordinate.setY(coordinate.getY() - migrationSpeed);
            migratedDistance +=migrationSpeed;
        }
        if(migratedDistance > migrationDistance){
            isMigrating = false;
        }
        return coordinate;
    }

}
