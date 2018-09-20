export class OutputConfiguration {
  configName: string;
  isWorking: boolean;

  constructor(
    configName: string,
    isWorking: boolean
  ) {
    this.configName = configName;
    this.isWorking = isWorking;
  }

}
