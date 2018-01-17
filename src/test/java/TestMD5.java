import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class TestMD5 {
	@Test
	public void tsetMd5() throws Exception{
		String file = "passwd";
		FileInputStream in = new FileInputStream(file);
		//计算文件的摘要
		//linux计算摘要命令：md5sum passwd
		//win : md5 passwd
		String md5 = DigestUtils.md5Hex(in);
		System.out.println(md5);
		in.close();
	}
	@Test
	public void testStringMd5() {
		String password ="1234";
		String md5 = DigestUtils.md5Hex(password);
		System.out.println("1234:"+":"+md5);
		
		String salt = "瓦卡卡卡卡";
		md5 = DigestUtils.md5Hex(password+salt);
		System.out.println("add salt:"+":"+md5);
	}
}
