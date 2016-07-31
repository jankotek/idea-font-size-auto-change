Thos plugin changes Idea font size if retina display is detected. If not detected, it changes font size back.
I wrote it because I was tired to change font size, every time I plug external dispay
<p/>
There is no configuration window, but you can change settings by editing 'fontsize/fontsize.properties' file
in the jar. Default settings is hardcoded for Dell XPS 13 with retina resolution 3200x1200 and font size 22.
For normal display it changes font size to 12.
<p/>
Java Display API is mostly broken, so this plugin is hack, but it works on my system :-)
<p/>
There is no reliable way to detect display DPI, so it uses display resolution to identify retina display.
This plugin checks every 5 seconds if any of connected displays has retina resolution.
Newly attached displays might not be detected, so make sure Idea starts with main display enabled.
<p/>
TODO use xrandr to get list of displays on Linux.


Howto use this plugin
-----------------------

This plugin works in two scenarios:

- You change display resolution on your laptop screen on your battery. It works automatically.

- You plug external display with low DPI. In this case your main display must change resolution, or be disabled, for this plugin to detect change.

Problems
-----------

- Newly pluged displays might not be detected. Make sure that your main retina display is enabled when Idea starts

- Retina resolution is hardcoded to 3200x1200. You will have to edit `fontsize/fontsize.properties`