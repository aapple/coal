import env from './env'

const DEV_URL = 'http://localhost:8089/'
const PRO_URL = 'http://119.29.250.146:84'

export default env === 'development' ? DEV_URL : PRO_URL
