package io.insomniac.thelostpages.client.renderer;

import io.insomniac.thelostpages.TheLostPages;
import io.insomniac.thelostpages.client.model.BookTestModel;
import io.insomniac.thelostpages.client.renderer.renderstates.BookTestEntityRenderState;
import io.insomniac.thelostpages.entities.BookTestEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;


public class BookTestEntityRenderer extends MobRenderer<BookTestEntity, BookTestEntityRenderState, BookTestModel> {
	private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TheLostPages.MOD_ID, "textures/entity/conjured_book.png");

	public BookTestEntityRenderer(EntityRendererProvider.Context context) {
		super(context, new BookTestModel(context.bakeLayer(ModEntityModelLayers.CONJURED_BOOK)), 0.375f); // 0.375 shadow radius
	}

	@Override
	public BookTestEntityRenderState createRenderState() {
		return new BookTestEntityRenderState();
	}

	@Override
	public Identifier getTextureLocation(BookTestEntityRenderState state) {
		return TEXTURE;
	}
}