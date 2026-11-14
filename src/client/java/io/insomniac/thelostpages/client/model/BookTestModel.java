package io.insomniac.thelostpages.client.model;

import io.insomniac.thelostpages.client.renderer.renderstates.BookTestEntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.EntityModel;

// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class BookTestModel extends EntityModel<BookTestEntityRenderState> {
	public final ModelPart L;
	public final ModelPart R;

	public BookTestModel(ModelPart root) {
		super(root);
		this.L = root.getChild("L");
		this.R = root.getChild("R");
	}

	public static LayerDefinition getTexturedModelData() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition L = partdefinition.addOrReplaceChild("L",
				CubeListBuilder.create().texOffs(9, 6).addBox(-6.0F, -5.0F, 0.0F, 6.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(10, 17).addBox(-5.0F, -4.0F, -1.0F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition R = partdefinition.addOrReplaceChild("R",
				CubeListBuilder.create().texOffs(9, 6).mirror().addBox(0.0F, -5.0F, 0.0F, 6.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false).texOffs(10, 17).addBox(0.0F, -4.0F, -1.0F, 5.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 20.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}
}