package about;

import xray.Xray;

/**
 * The "About section" class of the app.
 * Contains data about developer, version and license and more.
 * @author PilzHere
 *
 */
public class About {

//	These strings are collected by XrayController.
	public String aboutVersion;
	public String aboutDeveloper;
	public String aboutDate;
	public String aboutLicense;
	
	/**
	 * Sets data to class variables.
	 * Such as version, developer and license.
	 */
	public void setAboutData() {
		aboutVersion = Xray.VERSION;
		aboutDeveloper = Xray.DEVELOPER;
		aboutDate = "2017-12-07";
		String year = "2017";
		
		aboutLicense = "The MIT License (MIT)" + "\n" +
						"\n" +
						"Copyright (c) " + year + " " + aboutDeveloper + "\n" +
						"\n" +
						"Permission is hereby granted, free of charge, to any person obtaining a copy" + "\n" +
						"of this software and associated documentation files (the " + "\"Software\"" + "), to deal" + "\n" +
						"in the Software without restriction, including without limitation the rights" + "\n" +
						"to use, copy, modify, merge, publish, distribute, sublicense, and/or sell" + "\n" +
						"copies of the Software, and to permit persons to whom the Software is" + "\n" +
						"furnished to do so, subject to the following conditions:" + "\n" +
						"\n" +
						"The above copyright notice and this permission notice shall be included in" + "\n" +
						"all copies or substantial portions of the Software." + "\n" +
						"\n" + 
						"THE SOFTWARE IS PROVIDED" + "\"AS IS\"" + ", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR" + "\n" +
						"IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY," + "\n" +
						"FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE" + "\n" +
						"AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER" + "\n" +
						"LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM," + "\n" +
						"OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN" + "\n" +
						"THE SOFTWARE.";
	}
}
