Micropolis Project Progress Report
==================================

## Milestones Achieved:

Developed user interface independent MicropolisCore C++ and Python modules. 

Built two architecturally different playable user interfaces, including a cross platform desktop version (GTK/Cairo), and a
 networked web based version (TurboGears/OpenLaszo/Flash).

Building on top of a carefully chosen, well proven set of open source, cross platform tools and libraries.

    Linux. MySQL. Apache. GCC. Cairo. Pango. GTK. SWIG. Python. 
    PyCairo. PyGTK. TurboGears. Genshi. SQLAlchemy. CherryPy. PyAMF. 
    AMFast. Pie menus. OpenLaszlo. Red5. Java. Tomcat. WikiMedia. PHP. 
    Subversion. Doxygen.

Easily deployed on Amazon Elastic Computing Servers.

Overhauled and modernized the original SimCity source code.

    Rewrote the C code in C++.

    Performed overall cleaning up, refactoring and modularization of
    the code.

    Applied good C++ coding practices to increase robustness and
    maintainability.

    Applied sensible style guidelines and consistent naming schemes,
    to increase readability and learnability.

    Using doxygen to generate documentation from comments in the code.

    Wrote comments to document variables, functions and parameters,
    and explain how the code works.

    Wrote documentation, design plans, references and tutorials on the
    wiki.

    Pluged the C++ code into Python, and integrate it with the
    scripting language's object system.

    Refactored the user interface and graphics code as general purpose
    reusable C++ and Python components.

        Application logic, messaging, graphics and user interface
        re-implemented in portable Python code.

        Cairo graphics drawing code to visualize data, draw graphs,
        sprites, etc.

        Tile engine implemented in C++, using Cairo, plugged into
        Python, usable by other applications.

        GTK desktop user interface.

            Efficiently renders with Cairo and TileEngine.

            Portable to Windows, Mac, Linux, X11, etc.

            Used to quickly flesh out all the game features and prove
            the MicropolisEngine API, but not the focus of this
            project, which is really about the web based version.

        TurobGears/Python web server and OpenLaszlo/Flash user
        interface.

            Runs multiple simulators.

            Renders graphics with tile engine and Cairo.

            Uses AMF to efficiently send binary messages back and
            forth bewteen Python server and Flash client.

            WikiMedia extension to embed OpenLaszlo applications with
            live Micropolis views in wiki pages.

Summary of the web based version of Micropolis.

    All of the following software is open source, except for the Adobe
    Flash player required on the client.

    Server:

        Server runs on standard LAMP stack on Linux, MacOS or Windows
        (although the Windows build is not up to date).

        Apache web server. 

        MySQL database.

        WikiMedia and PHP to run it.

        GCC C++ compiler.

        Subversion source code control system.

        Doxygen documentation system.

        Python programming language.

            TurboGears web framework.

            CherryPy web application server.

            Genshi xml template system.

            SQLAlchemy object relational mapper and database toolkit.

            C++ or native code Python modules:

                SWIG: tool for integrating C++ code with Python and
                other languages.

                MicropolisCore: core Micropolis simulator engine.

                TileEngine: tile rendering engine and network
                compression module.

                Cairo, Pango: graphics and text rendering engines.

                PyAMF: Flash binary AMF networking protocol support.

        Internationalization.

            Web based tool for reviewing and editing language translations.

    Client:

        OpenLaszlo rich internet application development platform.

            JavaScript programming language.

            XML files for packaging lzx code and describing data.

            Laszlo presentation server and lzx compiler.

                Java software development kit.

                Tomcat web application server.

            AMF efficient binary networking protocol directly
            supported by Flash.

            Pie menu gestural user interface widget.

            Internationalization.

                The user interface is internationalized, so all
                strings displayed to the user are defined in XML files
                for each supported language.

                Currently English and Dutch are supported, but other
                languages can be added.

Features of the OpenLaszlo/Flash based Micropolis client.

    Two way asynchronous JSON messaging protocol over AMF.

        Represents messages as JSON data on client, Python data on
        server.

        Sends messages between Flash client and Python server via
        binary AMF networking protocol, over HTTP requests.

        Efficient AMF protocol built into Flash client.

        Efficient PyAMF module linked into Python web server.

    Scrolling tile view.

        Designed to make efficient use of network bandwidth and server
        resources, by implementing a compact application specific
        protocol, and offloading processing to the client (the essence
        of "good AJAX").

        The client caches a sparse array of tiles sent by the server,
        which are shared among views, and animated locally.

        Multiple views all tell the server which rectangles they're
        looking at.

        The server keeps track of tiles sent to the client, and sends
        just the differences for the tiles that are being viewed.

        The server and client use an efficient binary compression
        protocol to encode tile changes.

        C++ code in the TileEngine compares previous and current tiles
        in view, and efficiently encodes an application specific
        binary difference protocol to send to the client.

        JavaScript code in the Flash client decodes the tile
        difference protocol, updates the tile cache, and continuously
        animates the tile views locally. The Flash JIT makes it quite
        practical to implement binary protocol decoders in JavaScript.

        The client continuously updates local tile animations
        (traffic, smoke stacks, etc), so the animations run at a
        smooth frame rate, and the server doesn't have to send
        differences for tile animations. This significantly reduces
        the size of the update messages.

    Map with overlays.

        The scrolling tile view is good for close-up views of the map,
        but it does not scale well to situations with very many tiles,
        as when viewing the entire map. In that case it's much more
        efficient to render a bitmap on the server and send a
        compressed png file to the client, instead of the client
        receiving and drawing 120 * 100 = 12,000 individual tiles.

        The server uses Cairo and the TileEngine to render entire maps
        of various sizes in memory, with optional data visualization
        overlays. The TileEngine renders the scaled tiles as well as
        the partially transparent, lower resultion data overlays. It
        uses a tile mapping Python function to dynamically compute the
        color and transparency of the overlay, based on the map data
        layers in the simulator. So it's possible to script new
        visualizations in Python.

    User Interface

        Start Screen

            City title text view at top left.

            Map view below.
                Click to play.

            "Play With This Map" button below.
                Click to play.

            Description text view at bottom.

            Tabs at right:

                Scenarios

                    8 scenarios. Click button with icon to load scenario.

                Generator

                    Click button to generate random map.

                Library

                    List of "My Cities" if logged in.

                    List of shared cities.

                    Click city name to load.

                    When a city that you own is selected, you can "Configure Your City":

                        "Title" text field.
                        "Description" text field.
                        "Share My City" checkbox.
                        "Created" date label.
                        "Modified" date label.
                        "Delete My City" button.

                Login / User

                    When not logged in:

                        "User Name" text field.
                        "Password" secret text field.
                        "Log In" button.
                        "Repeat Password" text field.
                        "Create New Account" button.

                    Logged in:

                        "User Name" label.
                        "Full Name" text field.
                        "Email" text field.
                        "Change Password" button.
                        "Log Out" button.

        Play Screen

            The screen consists of three panels separated by gray
            edges that you can drag to resize, or click to toggle
            opened and closed.

            Tab panel along the top:

                Notices

                    Displays important messages, with an optional picture
                    or live aniamted map view centered on the event
                    location.

                Journal

                    Scrolling log of all simulator messages and chat text. 

                    Text field for entering chat messages and cheat codes.

                Evaluation

                    High level summary of how the mayor is doing and what
                    the main problems are.

                History

                    Graph of historic data about various aspects of the
                    simulation.

                Budget

                    Budget summary and slider controls to set the tax rate
                    and fund the city services.

                Overlays

                    Radio buttons to select the data visualization overlay
                    to display in the map view.

                Disasters

                    Buttons to trigger exciting disasters.

                Control

                    Control panel to pause and resume the simulation,
                    adjust the speed, save the city, select a new city,
                    get help and more information about Micropolis, and
                    select game play options.

                Login / User

                    Dialog to log in to an existing account, create a new
                    account, or configure the account you're logged in to.

            Scrolling map view at lower right.

                The tile view is continuously scrollable, and displays
                from a cache shared by all tile views, which is
                asynchronously updated from the server.

                Tile animation is handled locally and smoothly in the
                client, reducing network traffic and increasing
                responsiveness and local interactivity.

                The cursor, sprites (Monster, Tornado, etc), robots
                (PacBots, Avatars, etc) and user interface effects are
                drawn in an overlay above the tiles.

                The cursor follows the mouse, and shows the area on
                the grid, the name and the price of the currently
                selected tool.

                Edit the map with the currently selected tool by
                clicking or dragging with the left button.

                Hold the "Option" key and drag with left mouse button
                to pan the map.

                Hold the "Shift" key down and press the left mouse to
                pop up a pie menu for quickly selecting tools.

                    Some of the pie menu items open up sub-menus with
                    more selections.

                    You can click in the center of a pie menu to
                    cancel it.

        Control panel at the lower left.

            Map with overlay at top.

                The map view will resize with the width of the control
                panel, so you can make it big by dragging the resize
                edge all the way to the right.

                A black and white framed rectangle appears on the map,
                that shows the size and position of the scrolling map
                view, and which you can drag to scroll the map view.

                The map view can display various data visualization
                overlays, such as the population density, traffic rate
                or filtering different types of zones.

            Demand gauge and date and funds label, below.

                The demand gauge shows the demand (or oversupply) for
                residential, commercial and industrial zones.

                The current date and funds are displayed to the right
                of the demand gauge.

            Editing tool palette, at the bottom.

                Icons for the editing tools are displayed at the
                bottom of the panel.

                The currently selected tool is bright, and the others
                are dark.

                Selecting a tool with the pie menu is the same as
                selecting it from this palette, so the highlighting
                changes to reflect your pie menu tool selection.

                Once you learn to use the pie menus instead of the
                tool palette, you can close the control panel by
                clicking the vertical resize edge, and close the tab
                panel by clicking the horizontal resize edge, so the
                map will expand to the entire window.

                Editing tools:

                    Bulldozer
                        Destroys zones and features.

                    Road
                        Draws a road on land or a bridge over water.

                    Railroad
                        Draws a railroad on land or a tunnel under water.

                    Wire
                        Connects zones with power.

                    Park
                        Makes the city a nicer place to live.

                    Residential
                        Gives people a place to live. 
                        Generates citizens and traffic.

                    Commercial
                        Gives people a place to shop. 
                        Generates jobs and traffic.

                    Industrial
                        Gives people a place to work. 
                        Generates jobs and traffic.

                    Fire Station
                        Puts out fires nearby.

                    Police Station
                        Reduces crime nearby.

                    Stadium
                        Helps residential zones.

                    Seaport
                        Helps industrial zones.

                    Airport
                        Helps commercial zones.

                    Coal Power Plant
                        Generates power, but pollutes.

                    Nuclear Power Plant
                        Generates power, but melts down.

                    Query
                        Describes zones. (Not implemented yet.)

                    Center
                        Scrolls so clicked zone is at the center of
                        the screen.

                    PacBot
                        Follows roads towards heavy traffic, and eats
                        cars, reducing traffic.

                        First example of a programmable avatar, that
                        responds to the map and changes the simulation
                        data.

                        See the discussions with Alan Kay and others
                        about Logo turtles, visual programming
                        languages and Robot Odyssey.

                        http://www.micropolisonline.com/wiki/index.php/Alan_Kay

## Future plans.

Shared city library.

Journal, chat, IRC.

Deeper MediaWiki integration.

Multi user support.
        
        Avatar chat in game.
        Avatars as editing tools and programmable bots.
        Writing proposals.
        Campaigning for issues.
        Voting on proposals.
        Cooperative multi user interface.
        Writing down ideas, justifying your proposals to other players, and getting others to cooperate.
        Journalism and creative writing.
        City newspaper.
        Publish stories about cities in the wiki.
        Live playable views of save files associated with stories.

Facebook interface.
PayPal interface for micropayments to buy virtual money, cheats, high speed simulation, etc.
Voice and video conferencing.
Video playback for tutorials, reporting and education.
Amazon Web Services Elastic Computing Support.

        Load balancing.
        Clustering.

Online community.

        Sharing content.
        Rating. 
        Reporting.
        Discussion groups.
