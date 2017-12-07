package gpu;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import controllers.XrayController;

/**
 * The "Display section" class of the app.
 * Contains data about GPU card, vendor and OpenGL version.
 * @author PilzHere
 *
 */
public class Graphics {

//	These strings are collected by XrayController.
	public String gpuCard, gpuVendor, gpuOpenGLVersion;
	
//	The window handle
	private long window;
	
	public Graphics() {
		run();
	}
	
	/**
	 * Sets the GPU card name vendor and OpenGL version.
	 */
	private void setGpuData() {
		gpuCard = GL11.glGetString(GL11.GL_RENDERER);
		gpuCard = XrayController.helper.stringIsEmptyOrUnknownOrNull(gpuCard);
		
		gpuVendor = GL11.glGetString(GL11.GL_VENDOR);
		gpuVendor = XrayController.helper.stringIsEmptyOrUnknownOrNull(gpuVendor);
		
		gpuOpenGLVersion = GL11.glGetString(GL11.GL_VERSION);
		gpuOpenGLVersion = XrayController.helper.stringIsEmptyOrUnknownOrNull(gpuOpenGLVersion);
	}
	
	/*
	 * From here and down is literraly copied from LWJGL website on how to start OpengGL.
	 * Basically it creates a window -> activates openGL -> gets GPU data -> closes window -> terminates.
	 */
	
	public void run() {
//		System.out.println(Version.getVersion());
		
		init();
		loop();
		
//		Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);		
		glfwDestroyWindow(window);

//		Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}
	
	private void init() {
//		Setup an error callback. The default implementation
//		will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

//		Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

//		Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

//		Create the window
		window = glfwCreateWindow(1, 1, "XRay - Fetching GPU data...", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

//		Setup a key callback. It will be called every time a key is pressed, repeated or released.
//		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
//			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
//				glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
//		});

//		Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

//		Make the OpenGL context current
		glfwMakeContextCurrent(window);
//		Enable v-sync
		glfwSwapInterval(1);
		
//		Make the window visible
		glfwShowWindow(window);
	}
	
	private void loop() {
//		This line is critical for LWJGL's interoperation with GLFW's
//		OpenGL context, or any context that is managed externally.
//		LWJGL detects the context that is current in the current thread,
//		creates the GLCapabilities instance and makes the OpenGL
//		bindings available for use.
		GL.createCapabilities();

//		Set the clear color
//		glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

//		Let's gather some GPU data from here!
		setGpuData();
		
//		Closes this window.
		glfwSetWindowShouldClose(window, true);
		
//		Run the rendering loop until the user has attempted to close
//		the window or has pressed the ESCAPE key.
		while ( !glfwWindowShouldClose(window) ) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			glfwSwapBuffers(window); // swap the color buffers

//		Poll for window events. The key callback above will only be
//		invoked during this call.
		glfwPollEvents();
		}
	}
}
