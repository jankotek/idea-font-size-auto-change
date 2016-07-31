Changes Idea font size if retina display is detected. If not detected, it changes font size back.
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
