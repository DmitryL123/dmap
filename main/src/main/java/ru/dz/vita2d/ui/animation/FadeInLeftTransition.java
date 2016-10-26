package ru.dz.vita2d.ui.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TimelineBuilder;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author dmilut
 */

@SuppressWarnings("deprecation")
public class FadeInLeftTransition extends ConfigAnimation {

	public FadeInLeftTransition(final Node node) {
		super(node,
				TimelineBuilder.create()
						.keyFrames(
								new KeyFrame(Duration.millis(0), new KeyValue(node.opacityProperty(), 0, WEB_EASE),
										new KeyValue(node.translateXProperty(), -20, WEB_EASE)),
								new KeyFrame(Duration.millis(700), new KeyValue(node.opacityProperty(), 1, WEB_EASE),
										new KeyValue(node.translateXProperty(), 0, WEB_EASE)))
						.build());

		setCycleDuration(Duration.seconds(1));
		setDelay(Duration.seconds(0));
	}
}