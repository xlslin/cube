package com.sharingif.cube.web.handler.chain.command.token;

import com.sharingif.cube.communication.http.handler.HttpHandlerMethodContent;
import com.sharingif.cube.components.captcha.ICaptchaHandler;
import com.sharingif.cube.components.token.IToken;
import com.sharingif.cube.core.exception.CubeException;

/**
 *
 * @Description:  [生成验证码token，并保存到sesseion]
 * @Author:       [Joly]
 * @CreateDate:   [2014年5月29日 下午11:33:25]
 * @UpdateUser:   [Joly]
 * @UpdateDate:   [2014年5月29日 下午11:33:25]
 * @UpdateRemark: [说明本次修改内容]
 * @Version:      [v1.0]
 *
 */
public class CreateCaptchTokenWebCommand extends CreateTokenWebCommand {
	
	private ICaptchaHandler captchaHandler;
	
	public ICaptchaHandler getCaptchaHandler() {
		return captchaHandler;
	}
	public void setCaptchaHandler(ICaptchaHandler captchaHandler) {
		this.captchaHandler = captchaHandler;
	}
	
	@Override
	public void execute(HttpHandlerMethodContent content) throws CubeException {
		IToken token = getWebTokenManager().generateToken(content.getRequest());
		
		try {
			content.setArgs(new Object[]{captchaHandler.getCaptchByteStream(token.getUniqueId())});
		} catch (Exception e) {
			this.logger.error("build verification failed",e);
		}
	}

}
