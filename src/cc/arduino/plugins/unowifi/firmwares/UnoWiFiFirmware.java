/*
 * This file is part of WiFi101 Updater Arduino-IDE Plugin.
 * Copyright 2016 Arduino LLC (http://www.arduino.cc/)
 *
 * Arduino is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * As a special exception, you may use this file as part of a free software
 * library without restriction.  Specifically, if other files instantiate
 * templates or use macros or inline functions from this file, or you compile
 * this file and link it with other files to produce an executable, this
 * file does not by itself cause the resulting executable to be covered by
 * the GNU General Public License.  This exception does not however
 * invalidate any other reasons why the executable file might be covered by
 * the GNU General Public License.
 */
package cc.arduino.plugins.unowifi.firmwares;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.HashMap;

public class UnoWiFiFirmware {

	public static enum Conn {
		SERIAL,
		SPI
	}

	public static Map<String, String> fw1 = new HashMap<String, String>(){{
		put("0x00000","firmwares/20151223/boot_v1.4.bin_rep");
		put("0x1000","firmwares/20151223/user1.bin");
		put("0x3FC000","firmwares/20151223/esp_init_data_default.bin_rep");
		put("0x3FE000","firmwares/20151223/blank.bin");
	}};

	public static Map<String, String> fw2 = new HashMap<String, String>(){{
		put("0x00000","firmwares/20160318/boot_v1.4.bin_rep");
		put("0x1000","firmwares/20160318/user1.bin");
		put("0x3FC000","firmwares/20160318/esp_init_data_default.bin_rep");
		put("0x3FE000","firmwares/20160318/blank.bin");
	}};

	public static Map<String, String> fw3 = new HashMap<String, String>(){{
		put("0x00000","firmwares/20161121/boot_v1.4.bin_rep");
		put("0x1000","firmwares/20161121/user1.bin");
		put("0x3FC000","firmwares/20161121/esp_init_data_default.bin_rep");
		put("0x3FE000","firmwares/20161121/blank.bin");
	}};

	public static UnoWiFiFirmware available[] = new UnoWiFiFirmware[] {
		new UnoWiFiFirmware("ESP8266", "0.0.3", Conn.SERIAL, fw3),
		new UnoWiFiFirmware("ESP8266", "0.0.2", Conn.SERIAL, fw2),
		new UnoWiFiFirmware("ESP8266", "0.0.1", Conn.SERIAL, fw1)
	};


	public String name;
	public String version;
	public Conn conn;
	public Map<String, File> addrFile;

	public UnoWiFiFirmware(String _name, String _version,  Conn _conn, Map<String, String> _addr_filename) {
		addrFile = new HashMap<String, File>();
		name = _name;
		version = _version;
		conn = _conn;
		try {
			String jarPath = UnoWiFiFirmware.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			File jarFolder = new File(jarPath).getParentFile();

			for (String key : _addr_filename.keySet()) {
				File f = new File(jarFolder, _addr_filename.get(key));
				addrFile.put(key, f);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return name + " (" + version + ")";
	}

//	public byte[] getData() throws IOException {
//		InputStream in = null;
//		try {
//			in = new FileInputStream(file);
//			ByteArrayOutputStream res = new ByteArrayOutputStream();
//			byte buff[] = new byte[4096];
//			while (in.available() > 0) {
//				int read = in.read(buff);
//				if (read == -1)
//					break;
//				res.write(buff, 0, read);
//			}
//			return res.toByteArray();
//		} finally {
//			if (in != null)
//				in.close();
//		}
//	}

//	public File getFile() {
//		return file;
//	}

//	public File getFileUser1() {
//		return fileUser1;
//	}
//
//	public File getFileBlank() {
//		return fileBlank;
//	}
//
//	public File getFileBoot() {
//		return fileBoot;
//	}
//
//	public File getFileEspInit() {
//		return fileEspInit;
//	}

	public Map<String, File> getFiles(){
		return addrFile;
	}
}
