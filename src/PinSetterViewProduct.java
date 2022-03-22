

import javax.swing.JFrame;
import java.awt.HeadlessException;

public class PinSetterViewProduct {
	private final JFrame frame;

	public PinSetterViewProduct(JFrame JFrame) throws HeadlessException {
		frame = JFrame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void show() {
		frame.setVisible(true);
	}

	public void hide() {
		frame.setVisible(false);
	}
}