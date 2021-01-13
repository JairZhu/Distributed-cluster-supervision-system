<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="IP地址" prop="ip">
        <el-input v-model="ruleForm.ip"></el-input>
      </el-form-item>
      <el-form-item label="客户端名称" prop="name">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="CPU核数" prop="cpu">
        <el-input v-model="ruleForm.cpu"></el-input>
      </el-form-item>
      <el-form-item label="操作系统" prop="os">
        <el-select v-model="ruleForm.os" placeholder="请选择操作系统">
          <el-option label="Windows" value="Windows"></el-option>
          <el-option label="CentOS" value="CentOS"></el-option>
          <el-option label="Ubuntu" value="Ubuntu"></el-option>
          <el-option label="macOS" value="macOS"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
export default {
  name: "Settings",
  data() {
    return {
      ruleForm: {
        ip: '',
        name: '',
        cpu: '',
        os: '',
      },
      rules: {
        ip: [
          {required: true, message: '请输入IP地址', trigger: 'blur'},
          {pattern: '(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}', message: '请输入正确IP地址', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入客户端名称', trigger: 'blur'},
          {pattern: '^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8}$', message: '长度为8个字符', trigger: 'blur'}
        ],
        cpu: [
          {required: true, message: '请输入CPU核数', trigger: 'blur'},
          {pattern: '^[1-9]\\d*$', message: '请输入数字', trigger: 'blur'}
        ],
        os: [
          {required: true, message: '请选择操作系统', trigger: 'change'}
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm);
          this.$http.post("http://localhost:8011/saveClient", this.ruleForm);
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style scoped>

</style>
