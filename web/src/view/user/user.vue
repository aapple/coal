<template>
  <div>
    <Row>
      <Col span="2" class="ivu_title">用户名</Col>
      <Col>
        <Input v-model="userName" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">密码</Col>
      <Col>
      <Input v-model="password" clearable style="width: 200px"></Input>
      </Col>
    </Row>
    <br>
    <Row>
      <Col span="2" class="ivu_title">角色</Col>
      <Col>
        <Select v-model="role" style="width:200px" >
          <Option value="煤矿">煤矿</Option>
          <Option value="兰炭">兰炭</Option>
          <Option value="物流">物流</Option>
        </Select>
      </Col>
    </Row>

    <br>
    <br>
    <Row>
      <Button type="primary" @click="update">提交</Button>
    </Row>

  </div>

</template>

<script>
import { getTableDataFromArray } from '@/libs/util'
import { updateUser } from '@/api/coal'
export default {
  name: 'user_page',
  components: {

  },
  data () {
    return {
      userName: this.$route.params.userName,
      password: this.$route.params.password,
      role: this.$route.params.role
    }
  },
  methods: {
    update () {
      var data = {
        userName: this.userName,
        password: this.password,
        role: this.role
      };
      updateUser(data).then(res => {
        this.$Message.info("更新成功！");
        this.$router.push({
          name: 'userList_page'
        })
        resolve()
      }).catch(err => {

      })
    }
  }
}
</script>

<style lang="less">
  .ivu_title {
    padding-top: 5px;
  }
</style>
