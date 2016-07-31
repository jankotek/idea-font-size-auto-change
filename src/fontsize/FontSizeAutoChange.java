package fontsize;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

/**
 * <h3>FontSizeAutoChange</h3>
 */
public class FontSizeAutoChange implements ApplicationComponent {

    protected int lastWidth = -1;
    protected int lastHeight = -1;
    protected Timer t;
    protected int retinaWidth;
    protected int retinaHeight;
    protected int retinaFontSize;
    protected int normalFontSize;
    boolean oldRetina = false;


    public FontSizeAutoChange() throws IOException {
        super();

        Properties p = new Properties();
        p.load(getClass().getResourceAsStream("fontsize.properties"));

        retinaHeight = Integer.valueOf(p.getProperty("retinaHeight"));
        retinaWidth = Integer.valueOf(p.getProperty("retinaWidth"));
        retinaFontSize = Integer.valueOf(p.getProperty("retinaFontSize"));
        normalFontSize = Integer.valueOf(p.getProperty("normalFontSize"));
    }

    public void initComponent() {


        t = new Timer(5000, e -> {

            DisplayMode dm = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
            if (dm.getWidth() == lastWidth && dm.getHeight() == lastHeight) {
                return;
            }
            lastWidth = dm.getWidth();
            lastHeight = dm.getHeight();

            boolean retina = dm.getWidth() == retinaWidth && dm.getHeight() == retinaHeight;
            oldRetina = retina;
            int newFontSize = retina ? retinaFontSize : normalFontSize;
            UISettings.getInstance().FONT_SIZE = newFontSize;
            UISettings.getInstance().OVERRIDE_NONIDEA_LAF_FONTS = true;

//            LafManager.getInstance().updateUI();
//            LafManager.getInstance().repaintUI();

            EditorColorsManager.getInstance().getGlobalScheme().setEditorFontSize(newFontSize);
            EditorColorsManager.getInstance().getGlobalScheme().setConsoleFontSize(newFontSize);

            EditorColorsScheme oldScheme = EditorColorsManager.getInstance().getGlobalScheme();

            //set different scheme to apply settings
            for (EditorColorsScheme otherScheme : EditorColorsManager.getInstance().getAllSchemes()) {
                if (otherScheme != oldScheme) {
                    EditorColorsManager.getInstance().setGlobalScheme(otherScheme);
                    break;
                }
            }
            EditorColorsManager.getInstance().setGlobalScheme(oldScheme);
        });

        t.setDelay(5000);
        t.setInitialDelay(5000);
        t.setRepeats(true);
        t.start();
    }

    @Override
    public void disposeComponent() {
        if (t != null)
            t.stop();
        t = null;
    }


    public String getComponentName() {
        return "FontSizeAutoChange";
    }


}

