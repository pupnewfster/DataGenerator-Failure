package com.example.datagen_failure;

import java.util.function.Consumer;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod("datagen_failure")
@Mod.EventBusSubscriber(modid = "datagen_failure", bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenFailureMod {

    public DataGenFailureMod() {
    }

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        if (event.includeServer()) {
            //Server side data generators
            gen.addProvider(new FailingRecipeProvider(gen));
        }
    }

    private static class FailingRecipeProvider extends RecipeProvider {

        public FailingRecipeProvider(DataGenerator gen) {
            super(gen);
        }

        @Override
        protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
            ShapedRecipeBuilder.shapedRecipe(Items.SAND)
                  .key('C', Tags.Items.COBBLESTONE)
                  .patternLine("C")
                  .patternLine("C")
                  .addCriterion("has_cobble", hasItem(Tags.Items.COBBLESTONE))
                  .build(consumer);
        }
    }
}