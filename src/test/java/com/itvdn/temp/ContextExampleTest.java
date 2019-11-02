package com.itvdn.temp;

import com.itvdn.config.AppConfig;
import com.itvdn.config.DataConfig;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


// mockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class, DataConfig.class})
public class ContextExampleTest {

}
