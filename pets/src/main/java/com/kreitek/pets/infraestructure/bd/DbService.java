package com.kreitek.pets.infraestructure.bd;

import com.kreitek.pets.domain.Cat;
import com.kreitek.pets.domain.Dog;

import java.util.ArrayList;
import java.util.List;

public class DbService {
    public static volatile DbService logger = null;
    public static int cantidad;
    private List<Cat> cats = new ArrayList<>();
    private List<Dog> dogs = new ArrayList<>();

    public DbService() {
        if (logger != null) {
            throw new RuntimeException("Usage getInstance() method to create");
        }
    }

    public static DbService getInstance() {
        if (logger == null) {
            synchronized(DbService.class) {
                if (logger == null) {
                    logger = new DbService();
                }
            }
        }
        return logger;
    }

    public void debug(String txt)
	{
		cantidad++;
		System.out.println("[debug]" + "[" + cantidad + "]-" + txt);
	}

    public void addNewDog(Dog dog) {
        dogs.add(dog);
        logger.debug("BdService.Dog added");
    }
    public void addNewCat(Cat cat) {
        cats.add(cat);
        logger.debug("BdService.Cat added");
    }

    public List<Cat> getCats() {
        logger.debug("BdService.Get " + cats.size() + " cats");
        return new ArrayList<>(cats);
    }

    public List<Dog> getDogs() {
        logger.debug("BdService.Get " + cats.size() + " dogs");
        return new ArrayList<>(dogs);
    }
}
