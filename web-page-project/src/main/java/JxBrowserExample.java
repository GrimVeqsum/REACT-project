/*
 *  Copyright 2024, TeamDev. All rights reserved.
 *
 *  Redistribution and use in source and/or binary forms, with or without
 *  modification, must retain the above copyright notice and the following
 *  disclaimer.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 *  OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *  OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import static com.teamdev.jxbrowser.engine.RenderingMode.HARDWARE_ACCELERATED;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.view.swing.BrowserView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This example demonstrates how to initialize Chromium, create a browser instance
 * (equivalent of the Chromium tab), embed a Swing BrowserView component into Java Swing
 * frame to display content of the loaded web page, load the required web page.
 */
final class JxBrowserExample {
    private static final String URL = "http://EpicAddrLent"; // Укажите нужный URL
    public static void main(String[] args) {
        // Initialize Chromium.
        var options = EngineOptions.newBuilder(HARDWARE_ACCELERATED)
                .licenseKey("OK6AEKNYF2UTHKDE07FNU7YSS5I9IN8MS9WY3AHU0X37E6FRVTMN0LQ5TKXC97QZ0KYXWMGOJ7P4EHG3AMKVRKS0S7350IAXPZ3ZGBAGD5PM5JF0R5FRBFPXA5BGPC7YD47DGQXUFXDS9UX1K")
                .build();
        var engine = Engine.newInstance(options);


        // Create a Browser instance.
        Browser browser = engine.newBrowser();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JxBrowser AWT/Swing");
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    // Shutdown Chromium and release allocated resources.
                    engine.close();
                }
            });
            // Create and embed Swing BrowserView component to display web content.
            frame.add(BrowserView.newInstance(browser));
            frame.setSize(1280, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Load the required web page.
            browser.navigation().loadUrl("http://127.0.0.1:5500/index.html");
        });
    }
}
