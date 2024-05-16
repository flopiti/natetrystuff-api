
package com.natetrystuff.Ingredient;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> listAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public Ingredient findByName(String ingredientName) {
        return ingredientRepository.findByIngredientName(ingredientName);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredientDetails) {
        Ingredient existingIngredient = ingredientRepository.findById(id).orElse(null);
        if (existingIngredient != null) {
            existingIngredient.setIngredientName(ingredientDetails.getIngredientName());
            return ingredientRepository.save(existingIngredient);
        }
        throw new RuntimeException("Ingredient not found with id " + id);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
